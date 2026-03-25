
package com.bookings.inventoryservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookings.inventoryservice.entity.Event;

/**
 * EventRepository
 */
@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

}
