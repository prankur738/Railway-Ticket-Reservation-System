package com.learning.trainservice.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SearchTrainsRequest {
    @Pattern(regexp="[A-Z]{2,4}", message="Invalid station-code format")
    private String fromStation;

    @Pattern(regexp="[A-Z]{2,4}", message="Invalid station-code format")
    private String toStation;

    //The ISO 8601 format for dates is "YYYY-MM-DD"
    private LocalDate departureDate;
}
