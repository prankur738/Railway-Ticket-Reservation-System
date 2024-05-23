package com.learning.trainservice.entity;

import com.learning.trainservice.enums.BookingStatus;
import com.learning.trainservice.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * This entity represents seats that have been confirmed as booked on a train.
 * It assists in determining the available seats for a given train.
 */
@Entity
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SeatBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;

    private LocalDate trainStartDate;

    @Column(name = "`from`") // Use backticks to escape reserved keyword
    private String from; //departure-station-code

    @Column(name = "`to`") // Use backticks to escape reserved keyword
    private String to;    //arrival-station-code

    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    private Long bookingId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private BookingStatus bookingStatus = BookingStatus.NOT_BOOKED;

}
