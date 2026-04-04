package com.bookings.bookingservice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookings.bookingservice.request.BookingRequest;
import com.bookings.bookingservice.response.BookingResponse;
import com.bookings.bookingservice.service.BookingService;

/**
 * BookingController
 */
@RestController
@RequestMapping("/api/v1")
public class BookingController {

	private final BookingService bookingService;

	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}

	@PostMapping(consumes = "application/json", produces = "application/json", path = "/bookings")
	public BookingResponse createBooking(@RequestBody BookingRequest bookingRequest) {
		return bookingService.createBooking(bookingRequest);
	}
}
