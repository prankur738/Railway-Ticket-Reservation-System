package com.learning.trainservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class TrainFrequency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;

    /** Numeric value of dayOfWeek
    /** Monday-0, Tuesday-1, ..... ,Saturday-5, Sunday-6 */
    private Integer dayOfWeek;

    private Boolean isRunning;
}
