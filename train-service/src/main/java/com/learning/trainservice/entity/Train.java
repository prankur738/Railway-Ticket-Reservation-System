package com.learning.trainservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter @Setter
@Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "train")
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "train_name")
    private String trainName;

    @Column(name = "start_station_code")
    private String startStationCode;

    @Column(name = "end_station_code")
    private String endStationCode;

    @Column(name = "start_time")
    private LocalTime startTime;

    @Column(name = "end_time")
    private LocalTime endTime;

    @Builder.Default
    @Column(name = "start_day")
    private Integer startDay = 1;

    @Column(name = "end_day")
    private Integer endDay;

    @Column(name = "total_distance")
    private Integer totalDistance; //in Kms

}
