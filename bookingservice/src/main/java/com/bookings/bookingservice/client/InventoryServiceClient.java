package com.bookings.bookingservice.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bookings.bookingservice.response.InventoryResponse;

/**
 * InventoryServiceClient
 */
@Service
public class InventoryServiceClient {

	@Value("${inventory.service.url}")
	private String inventoryServiceUrl;

	public InventoryResponse getInventory(final Long eventId) {
		final RestTemplate restTemplate = new RestTemplate();
		return restTemplate.getForObject(inventoryServiceUrl + "/events/" + eventId, InventoryResponse.class);
	}

}
