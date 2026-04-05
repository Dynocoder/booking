
package com.bookings.orderservice.repository;

import org.springframework.stereotype.Repository;

import com.bookings.orderservice.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * OrderRepository
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
