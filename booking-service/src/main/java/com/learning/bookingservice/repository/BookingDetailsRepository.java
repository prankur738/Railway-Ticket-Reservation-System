package com.learning.bookingservice.repository;

import com.learning.bookingservice.model.BookingDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingDetailsRepository extends JpaRepository<BookingDetails, Long> {
}
