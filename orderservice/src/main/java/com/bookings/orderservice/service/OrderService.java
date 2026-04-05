
package com.bookings.orderservice.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.bookings.bookingservice.event.BookingEvent;
import com.bookings.orderservice.entity.Order;
import com.bookings.orderservice.repository.OrderRepository;
import com.bookings.orderservice.client.InventoryServiceClient;

import lombok.extern.slf4j.Slf4j;

/**
 * OrderService
 */
@Service
@Slf4j
public class OrderService {

	private final OrderRepository orderRepository;
	private final InventoryServiceClient inventoryServiceClient;

	public OrderService(
			final OrderRepository orderRepository,
			final InventoryServiceClient inventoryServiceClient) {
		this.orderRepository = orderRepository;
		this.inventoryServiceClient = inventoryServiceClient;
	}

	@KafkaListener(topics = "booking", groupId = "order-service")
	public void orderEvent(BookingEvent bookingEvent) {
		log.info("Received order event: {}", bookingEvent);

		Order order = createOrder(bookingEvent);

		// save the order to the db
		orderRepository.saveAndFlush(order);
		log.info("Order saved with ID: {}", order.getId());

		inventoryServiceClient.updateEventCapacity(bookingEvent.getEventId(), bookingEvent.getTicketCount());
		log.info("Updated inventory for event ID: {}", bookingEvent.getEventId());

	}

	private Order createOrder(BookingEvent bookingEvent) {
		return Order.builder()
				.customerId(bookingEvent.getUserId())
				.eventId(bookingEvent.getEventId())
				.quantity(bookingEvent.getTicketCount())
				.totalPrice(bookingEvent.getTotalPrice())
				.build();
	}
}
