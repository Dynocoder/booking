package com.bookings.inventoryservice.response;

import java.math.BigDecimal;

import com.bookings.inventoryservice.entity.Venue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class EventInventoryResponse {
	private Long eventId;
	private String event;
	private Long capacity;
	private Venue venue;
	private BigDecimal ticketPrice;
}
