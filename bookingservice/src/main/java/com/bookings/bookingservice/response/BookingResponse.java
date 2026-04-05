package com.bookings.bookingservice.response;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BookingResponse
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookingResponse {
	private Long userId;
	private Long eventId;
	private Long ticketCount;
	private BigDecimal totalPrice;

}
