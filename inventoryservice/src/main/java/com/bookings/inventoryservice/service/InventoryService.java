package com.bookings.inventoryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookings.inventoryservice.entity.Event;
import com.bookings.inventoryservice.repository.EventRepository;
import com.bookings.inventoryservice.repository.VenueRepository;
import com.bookings.inventoryservice.response.EventInventoryResponse;

/**
 * InventoryService
 */
@Service
public class InventoryService {

	private final EventRepository eventRepository;
	private final VenueRepository venueRepository;

	@Autowired
	public InventoryService(final EventRepository eventRepository, final VenueRepository venueRepository) {
		this.eventRepository = eventRepository;
		this.venueRepository = venueRepository;
	}

	public List<EventInventoryResponse> getAllEvents() {
		final List<Event> events = eventRepository.findAll();

		return events.stream().map(event -> EventInventoryResponse.builder()
				.event(event.getName())
				.capacity(event.getLeftCapacity())
				.venue(event.getVenue())
				.build()).collect(Collectors.toList());
	}

}
