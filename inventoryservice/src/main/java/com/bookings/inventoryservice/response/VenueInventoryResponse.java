
package com.bookings.inventoryservice.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * VenueInventoryResponse
 */
@Data
@Builder
@AllArgsConstructor
public class VenueInventoryResponse {
	private Long venueId;
	private String venueName;
	private Long totalCapacity;
}
