package com.learning.trainservice.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewStationRequest {

    @Pattern(regexp="[A-Z]{2,4}", message="Invalid station-code format")
    private String stationCode;

    @Pattern(regexp="[A-Z][A-Z ]*", message="Station name must consist of capital letters only")
    private String stationName;
}
