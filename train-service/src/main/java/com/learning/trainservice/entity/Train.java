package com.learning.trainservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainNumber;

    private String trainName;

    private String startStationCode;

    private String endStationCode;

    private LocalTime startTime;

    private LocalTime endTime;

    @Builder.Default
    private Integer startDay = 1;

    private Integer endDay;

    private Integer totalDistance; //in Kms

}
