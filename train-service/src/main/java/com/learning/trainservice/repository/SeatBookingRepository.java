package com.learning.trainservice.repository;

import com.learning.trainservice.dto.SeatDataDto;
import com.learning.trainservice.entity.SeatBooking;
import com.learning.trainservice.enums.BookingStatus;
import com.learning.trainservice.enums.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SeatBookingRepository extends JpaRepository<SeatBooking, Long> {

    @Query("SELECT new com.learning.trainservice.dto.SeatDataDto(" +
            "s.seatNumber, s.seatType) " +
            "FROM SeatBooking s " +
            "WHERE s.trainNumber = :trainNumber " +
            "AND s.trainStartDate = :trainStartDate " +
            "AND s.seatType = :seatType " +
            "AND s.bookingStatus = :bookingStatus")
    List<SeatDataDto> findSeatsByTrainNumberAndTrainStartDateAndSeatTypeAndBookingStatus(String trainNumber,
                                                                                         LocalDate trainStartDate,
                                                                                         SeatType seatType,
                                                                                         BookingStatus bookingStatus);


}
