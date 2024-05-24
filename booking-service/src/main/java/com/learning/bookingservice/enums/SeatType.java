package com.learning.bookingservice.enums;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@Getter
public enum SeatType {


    AC,
    SLEEPER;

    private float farePerKm;

    public void setFarePerKm(float farePerKm) {
        this.farePerKm = farePerKm;
    }

    public float getFare(int journeyDistanceInKms) {
        return this.farePerKm * journeyDistanceInKms;
    }
}
