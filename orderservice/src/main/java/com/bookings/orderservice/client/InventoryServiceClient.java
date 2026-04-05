
package com.bookings.orderservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * InventoryServiceClient
 */
@Service
public class InventoryServiceClient {

	@Value("${inventory.service.url}")
	private String inventoryServiceUrl;

	public ResponseEntity<Void> updateEventCapacity(Long eventId, Long capacity) {
		final RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(inventoryServiceUrl + "/events/" + eventId + "/capacity/" + capacity, null);
		return ResponseEntity.ok().build();
	}

}
