package com.learning.trainservice.entity;

import com.learning.trainservice.enums.SeatBookingStatus;
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
@Table(name = "seat_booking")
public class SeatBooking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "train_start_date")
    private LocalDate trainStartDate;

    @Column(name = "`from`") // Use backticks to escape reserved keyword
    private String from; //departure-station-code

    @Column(name = "`to`") // Use backticks to escape reserved keyword
    private String to;    //arrival-station-code

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "seat_type")
    private SeatType seatType;

    @Column(name = "booking_id")
    private Long bookingId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(name = "booking_status")
    private SeatBookingStatus seatBookingStatus = SeatBookingStatus.NOT_BOOKED;

}
