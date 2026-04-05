package com.bookings.bookingservice.event;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BookingEvent
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingEvent {
	private Long userId;
	private Long eventId;
	private Integer ticketCount;
	private BigDecimal totalPrice;
}
