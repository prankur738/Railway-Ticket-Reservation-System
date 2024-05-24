package com.learning.bookingservice.repository;

import com.learning.bookingservice.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

}
