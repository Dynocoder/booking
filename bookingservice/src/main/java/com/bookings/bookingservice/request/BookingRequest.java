package com.bookings.bookingservice.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BookingRequest
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingRequest {
	private Long userId;
	private Long eventId;
	private int ticketCount;
}
