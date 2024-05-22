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
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;

    private Integer seatNumber;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;
}
