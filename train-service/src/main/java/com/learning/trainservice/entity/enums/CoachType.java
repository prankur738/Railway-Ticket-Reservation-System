package com.learning.trainservice.entity.enums;

import lombok.Getter;

@Getter
public enum CoachType {
    GENERAL(0.3),
    SLEEPER(0.5),
    THIRD_AC(1.1),
    SECOND_AC(1.7),
    FIRST_AC(3.0);

    private final Double pricePerKm;

    CoachType(Double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

}
