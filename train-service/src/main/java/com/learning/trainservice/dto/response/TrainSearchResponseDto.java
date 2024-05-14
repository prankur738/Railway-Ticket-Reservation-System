package com.learning.trainservice.dto.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
public class TrainSearchResponseDto {
    private String fromStationCode;
    private String fromStationName;
    private String toStationCode;
    private String toStationName;
    private LocalDate departureDate;
    private List<TrainResponseDto> trains;
}
