package com.bookings.bookingservice.service;

import org.springframework.stereotype.Service;

import com.bookings.bookingservice.client.InventoryServiceClient;
import com.bookings.bookingservice.entity.Customer;
import com.bookings.bookingservice.repository.CustomerRepository;
import com.bookings.bookingservice.request.BookingRequest;
import com.bookings.bookingservice.response.BookingResponse;
import com.bookings.bookingservice.response.InventoryResponse;

/**
 * BookingService
 */
@Service
public class BookingService {

	private final CustomerRepository customerRepository;
	private final InventoryServiceClient inventoryServiceClient;

	public BookingService(
			final CustomerRepository customerRepository,
			final InventoryServiceClient inventoryServiceClient) {
		this.customerRepository = customerRepository;
		this.inventoryServiceClient = inventoryServiceClient;
	}

	public BookingResponse createBooking(final BookingRequest bookingRequest) {
		// Find the customer by ID
		final Customer customer = customerRepository.findById(bookingRequest.getUserId()).orElse(null);
		if (customer == null) {
			throw new RuntimeException("Customer not found with ID: " + bookingRequest.getUserId());
		}

		// Get the event inventory
		final InventoryResponse inventoryResponse = inventoryServiceClient.getInventory(bookingRequest.getEventId());
		System.out.println("Inventory Response: " + inventoryResponse);

		if (inventoryResponse.getCapacity() < bookingRequest.getTicketCount()) {
			throw new RuntimeException("Not enough tickets available for event ID: " + bookingRequest.getEventId());
		}

		return BookingResponse.builder().build();
	}
}
