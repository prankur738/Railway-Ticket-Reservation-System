package com.learning.trainservice.dto.response;

import com.learning.trainservice.dto.SeatDataDto;
import com.learning.trainservice.dto.TrainDataDto;
import com.learning.trainservice.enums.SeatType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * This class represents the response for a single train in the list of trains
 * within the SearchTrainsResponseDto. It includes details about the train's
 * journey, including departure and arrival times, distances, fares, and available seats.
 */
@Data
@Builder
public class TrainResponseDto {
    private String trainName;
    private String trainNumber;
    private String departureStationCode;
    private LocalDateTime departureTime;
    private String arrivalStationCode;
    private LocalDateTime arrivalTime;
    private Integer journeyDistanceInKms;

    private Float sleeperSeatFare;
    private Float acSeatFare;

    private List<SeatDataDto> availableSleeperSeats;
    private List<SeatDataDto> availableAcSeats;

    public static TrainResponseDto from(TrainDataDto trainData, String trainName, LocalDate departureDate) {
        LocalDateTime depTime = LocalDateTime.of(departureDate, trainData.getDepartureTime());
        LocalDateTime arrTime = LocalDateTime.of(departureDate.plusDays(trainData.getArrivalTrainDay()- trainData.getDepartureTrainDay()), trainData.getArrivalTime());

        return TrainResponseDto.builder()
                .trainName(trainName)
                .trainNumber(trainData.getTrainNumber())
                .departureStationCode(trainData.getDepartureStationCode())
                .departureTime(depTime)
                .arrivalStationCode(trainData.getArrivalStationCode())
                .arrivalTime(arrTime)
                .journeyDistanceInKms(trainData.getJourneyDistanceInKms())
                .sleeperSeatFare(trainData.getJourneyDistanceInKms()* SeatType.SLEEPER.getFarePerKm())
                .acSeatFare(trainData.getJourneyDistanceInKms()*SeatType.AC.getFarePerKm())
                .build();
    }

}
