package com.bookings.inventoryservice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bookings.inventoryservice.entity.Event;
import com.bookings.inventoryservice.entity.Venue;
import com.bookings.inventoryservice.repository.EventRepository;
import com.bookings.inventoryservice.repository.VenueRepository;
import com.bookings.inventoryservice.response.EventInventoryResponse;
import com.bookings.inventoryservice.response.VenueInventoryResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * InventoryService
 */
@Service
@Slf4j
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

	public VenueInventoryResponse getVenueInfo(Long venueId) {
		final Venue venue = venueRepository.findById(venueId).orElse(null);

		return VenueInventoryResponse.builder()
				.venueId(venue.getId())
				.venueName(venue.getName())
				.totalCapacity(venue.getTotalCapacity())
				.build();
	}

	public EventInventoryResponse getEventInfo(Long eventId) {
		final Event event = eventRepository.findById(eventId).orElse(null);
		if (event == null) {
			throw new RuntimeException("Event not found with id: " + eventId);
		}

		return EventInventoryResponse.builder()
				.eventId(event.getId())
				.event(event.getName())
				.capacity(event.getLeftCapacity())
				.venue(event.getVenue())
				.ticketPrice(event.getTicketPrice())
				.eventId(event.getId())
				.build();
	}

	public void updateEventCapacity(Long eventId, Long capacity) {
		final Event event = eventRepository.findById(eventId).orElse(null);
		if (event == null) {
			throw new RuntimeException("Event not found with id: " + eventId);
		}

		event.setLeftCapacity(event.getLeftCapacity() - capacity);
		eventRepository.saveAndFlush(event);

		log.info("Updated event capacity for eventId: {}, new capacity: {}", eventId, event.getLeftCapacity());
	}

}
