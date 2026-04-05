package com.bookings.bookingservice.service;

import java.awt.print.Book;
import java.math.BigDecimal;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.bookings.bookingservice.client.InventoryServiceClient;
import com.bookings.bookingservice.entity.Customer;
import com.bookings.bookingservice.event.BookingEvent;
import com.bookings.bookingservice.repository.CustomerRepository;
import com.bookings.bookingservice.request.BookingRequest;
import com.bookings.bookingservice.response.BookingResponse;
import com.bookings.bookingservice.response.InventoryResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * BookingService
 */
@Service
@Slf4j
public class BookingService {

	private final CustomerRepository customerRepository;
	private final InventoryServiceClient inventoryServiceClient;
	private final KafkaTemplate<String, BookingEvent> kafkaTemplate;

	public BookingService(
			final CustomerRepository customerRepository,
			final InventoryServiceClient inventoryServiceClient,
			final KafkaTemplate<String, BookingEvent> kafkaTemplate) {
		this.customerRepository = customerRepository;
		this.inventoryServiceClient = inventoryServiceClient;
		this.kafkaTemplate = kafkaTemplate;
	}

	public BookingResponse createBooking(final BookingRequest bookingRequest) {
		// Find the customer by ID
		final Customer customer = customerRepository.findById(bookingRequest.getUserId()).orElse(null);
		if (customer == null) {
			throw new RuntimeException("Customer not found with ID: " + bookingRequest.getUserId());
		}

		// Get the event inventory
		final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(bookingRequest.getEventId());
		log.info("Inventory Response: {}", inventoryResponse);

		if (inventoryResponse.getCapacity() < bookingRequest.getTicketCount()) {
			throw new RuntimeException("Not enough tickets available for event ID: " + bookingRequest.getEventId());
		}

		// Send async booking event to Kafka
		final BookingEvent bookingEvent = createBookingEvent(bookingRequest, customer, inventoryResponse);

		kafkaTemplate.send("booking", bookingEvent);
		log.info("Booking event sent to Kafka: {}", bookingEvent);

		return BookingResponse.builder()
				.userId(customer.getId())
				.eventId(bookingRequest.getEventId())
				.ticketCount(bookingRequest.getTicketCount())
				.totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())))
				.build();
	}

	private BookingEvent createBookingEvent(final BookingRequest bookingRequest,
			final Customer customer,
			final InventoryResponse inventoryResponse) {
		return BookingEvent.builder()
				.userId(customer.getId())
				.eventId(bookingRequest.getEventId())
				.ticketCount(bookingRequest.getTicketCount())
				.totalPrice(inventoryResponse.getTicketPrice().multiply(BigDecimal.valueOf(bookingRequest.getTicketCount())))
				.build();
	}
}
