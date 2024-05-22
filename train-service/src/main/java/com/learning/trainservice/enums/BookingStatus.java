package com.learning.trainservice.enums;

public enum BookingStatus {

    /**
     * Currently, only NOT_BOOKED seats are taken in consideration to fetch the available seats
     * in a train
     */
    NOT_BOOKED,
    CONFIRMED,
    CANCELLED,
    WAITING
}
