package com.learning.trainservice.enums;

public enum SeatBookingStatus {

    /**
     * only NOT_BOOKED seats are taken in consideration to fetch the available seats
     * in a train
     */

    /**
     * Indicates that the seat is currently not booked and available for reservation.
     * Only NOT_BOOKED seats are taken in consideration to fetch the available seats in a train
     */
    NOT_BOOKED,

    /**
     * Indicates that the seat has been booked.
     */
    BOOKED,

    /**
     * Indicates that the seat is awaiting payment confirmation.
     */
    AWAITING_PAYMENT
}
