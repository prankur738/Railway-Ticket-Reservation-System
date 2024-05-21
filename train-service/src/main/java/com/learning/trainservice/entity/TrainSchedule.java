package com.learning.trainservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TrainSchedule extends BaseModel {

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "station_code")
    private String stationCode;

    @Column(name = "arrival_time")
    private LocalTime arrivalTime;

    @Column(name = "departure_time")
    private LocalTime departureTime;

    @Column(name = "day")
    private Integer day;

    @Column(name = "distance_from_origin")
    private Integer distanceFromOrigin;
}
