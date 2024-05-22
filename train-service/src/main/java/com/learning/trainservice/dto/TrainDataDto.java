package com.learning.trainservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data @NoArgsConstructor
/** This DTO is used to fetch the details of a train running between two stations from TrainSchedule entity
 * Please maintain the field order as it defines the parameters for the
 * AllArgsConstructor used in TrainScheduleRepository.*/
public class TrainDataDto {
    private String trainNumber;

    private LocalTime departureTime;
    private Integer departureTrainDay; //train day on passenger-departure-station

    private LocalTime arrivalTime;
    private Integer arrivalTrainDay; //train day on passenger-arrival-station

    private Integer journeyDistanceInKms;

    private String departureStationCode;
    private String arrivalStationCode;

    public TrainDataDto(String trainNumber, LocalTime departureTime, Integer departureTrainDay, LocalTime arrivalTime, Integer arrivalTrainDay, Integer journeyDistanceInKms, String departureStationCode, String arrivalStationCode) {
        this.trainNumber = trainNumber;
        this.departureTime = departureTime;
        this.departureTrainDay = departureTrainDay;
        this.arrivalTime = arrivalTime;
        this.arrivalTrainDay = arrivalTrainDay;
        this.journeyDistanceInKms = journeyDistanceInKms;
        this.departureStationCode = departureStationCode;
        this.arrivalStationCode = arrivalStationCode;
    }
}
