package com.learning.trainservice.dto.response;

import com.learning.trainservice.dto.TrainDataDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TrainResponseDto {
    private String trainName;
    private String trainNumber;
    private String departureStationCode;
    private LocalDateTime departureTime;
    private String arrivalStationCode;
    private LocalDateTime arrivalTime;

    public static TrainResponseDto from(TrainDataDto trainData, String trainName, LocalDate departureDate) {
        LocalDateTime depTime = LocalDateTime.of(departureDate, trainData.getDepartureTime());
        LocalDateTime arrTime = LocalDateTime.of(departureDate.plusDays(trainData.getArrivalDay()- trainData.getDepartureDay()), trainData.getArrivalTime());

        return TrainResponseDto.builder()
                .trainName(trainName)
                .trainNumber(trainData.getTrainNumber())
                .departureStationCode(trainData.getDepartureStationCode())
                .departureTime(depTime)
                .arrivalStationCode(trainData.getArrivalStationCode())
                .arrivalTime(arrTime)
                .build();
    }

}
