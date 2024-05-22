package com.learning.trainservice.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalTime;

@Getter @Builder @Setter
@AllArgsConstructor @NoArgsConstructor
public class NewTrainRequest {

    @Pattern(regexp="\\d{5}", message="Train number must be 5 digits")
    private String trainNumber;

    @Pattern(regexp="[A-Z][A-Z ]*", message="Train name must consist of capital letters only")
    private String trainName;

    @Pattern(regexp="[A-Z]{2,4}", message="Invalid start station-code format")
    private String startStationCode;

    @Pattern(regexp="[A-Z]{2,4}", message="Invalid end station-code format")
    private String endStationCode;

    private LocalTime startTime;

    private LocalTime endTime;

    @Min(value = 1, message = "End day must be 1, 2, or 3")
    @Max(value = 3, message = "End day must be 1, 2, or 3")
    private Integer endDay;

    @NotNull @Positive
    private Integer totalDistance; //in Kms
}
