package com.bookings.bookingservice.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * VenueResponse
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueResponse {
	private Long id;
	private String name;
	private String address;
	private Long totalCapacity;
}
