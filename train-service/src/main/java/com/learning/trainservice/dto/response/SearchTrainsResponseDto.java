package com.learning.trainservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * This class is a response class for a search-request for trains between two stations on a particular date
 */
@Data
@Builder
public class SearchTrainsResponseDto {
    private String fromStationCode;
    private String fromStationName;
    private String toStationCode;
    private String toStationName;
    private LocalDate departureDate;
    private List<TrainResponseDto> trains;
}
