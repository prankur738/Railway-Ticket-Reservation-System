package com.learning.trainservice.repository;

import com.learning.trainservice.dto.SeatDataDto;
import com.learning.trainservice.entity.SeatBooking;
import com.learning.trainservice.enums.SeatBookingStatus;
import com.learning.trainservice.enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long> {

    @Query("SELECT new com.learning.trainservice.dto.SeatDataDto(" +
            "s.seatNumber, s.seatType) " +
            "FROM SeatBooking s " +
            "WHERE s.trainNumber = ?1 " +
            "AND s.trainStartDate = ?2 " +
            "AND s.seatType = ?3 " +
            "AND s.seatBookingStatus = ?4")
    List<SeatDataDto> findSeatsByTrainNumberAndTrainStartDateAndSeatTypeAndBookingStatus(String trainNumber,
                                                                                         LocalDate trainStartDate,
                                                                                         SeatType seatType,
                                                                                         SeatBookingStatus seatBookingStatus);


}
