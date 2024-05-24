package com.learning.trainservice.entity;

import com.learning.trainservice.enums.SeatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * This entity represents a seat in a train
 */
@Entity
@NoArgsConstructor @AllArgsConstructor
@Getter
@Table(name = "seat")
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "seat_type")
    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
