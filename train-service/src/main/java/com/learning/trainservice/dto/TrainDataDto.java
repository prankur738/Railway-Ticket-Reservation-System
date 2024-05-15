package com.learning.trainservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
@Data @NoArgsConstructor
@AllArgsConstructor
/** Please maintain the field order as it defines the parameters for the
 * AllArgsConstructor used in TrainScheduleRepository.*/
public class TrainDataDto {
    private String trainNumber;
    private LocalTime departureTime;
    private Integer departureDay;

    private LocalTime arrivalTime;
    private Integer arrivalDay;

    private String departureStationCode;
    private String arrivalStationCode;

}
