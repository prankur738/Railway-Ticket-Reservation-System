package com.learning.trainservice.dto.request;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TrainSearchRequest {
    @Pattern(regexp="[A-Z]{2,4}", message="Invalid station-code format")
    private String from;

    @Pattern(regexp="[A-Z]{2,4}", message="Invalid station-code format")
    private String to;
}
