package com.bookings.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookings.inventoryservice.entity.Venue;

/**
 * VenueRepository
 */
@Repository
public interface VenueRepository extends JpaRepository<Venue, Long> {

}
