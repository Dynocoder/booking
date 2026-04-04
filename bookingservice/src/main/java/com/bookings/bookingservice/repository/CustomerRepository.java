package com.bookings.bookingservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookings.bookingservice.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
