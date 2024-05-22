package com.learning.trainservice.repository;

import com.learning.trainservice.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByTrainNumber(String trainNumber);
}
