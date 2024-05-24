package com.learning.bookingservice.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

//import javax.annotation.PostConstruct;

import com.learning.bookingservice.enums.SeatType;

@Configuration
public class SeatTypeConfig {

    @Value("${seatType.ac.farePerKm}")
    private float acFarePerKm;

    @Value("${seatType.sleeper.farePerKm}")
    private float sleeperFarePerKm;

    @PostConstruct
    public void init() {
        SeatType.AC.setFarePerKm(acFarePerKm);
        SeatType.SLEEPER.setFarePerKm(sleeperFarePerKm);
    }
}

