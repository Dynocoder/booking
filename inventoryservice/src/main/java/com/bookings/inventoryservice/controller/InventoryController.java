package com.bookings.inventoryservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bookings.inventoryservice.response.EventInventoryResponse;
import com.bookings.inventoryservice.response.VenueInventoryResponse;
import com.bookings.inventoryservice.service.InventoryService;

@RestController
@RequestMapping("/api/v1")
public class InventoryController {

	private InventoryService inventoryService;

	public InventoryController(final InventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}

	@GetMapping("/inventory/events")
	public @ResponseBody List<EventInventoryResponse> inventoryGetAllEvents() {
		return inventoryService.getAllEvents();
	}

	@GetMapping("/inventory/venue/{venueId}")
	public @ResponseBody VenueInventoryResponse inventoryByVenueId(@PathVariable("venueId") Long venueId) {
		return inventoryService.getVenueInfo(venueId);
	}
}
