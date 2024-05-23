package com.learning.trainservice.enums;

import lombok.Getter;

@Getter
public enum SeatType {
    AC(5),

    SLEEPER(2);

    final float farePerKm;

     SeatType(float farePerKm) {
        this.farePerKm = farePerKm;
    }

}
