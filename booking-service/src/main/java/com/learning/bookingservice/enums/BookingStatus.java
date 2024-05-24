package com.learning.bookingservice.enums;

public enum BookingStatus {
    CONFIRMED,
    PAYMENT_PENDING,
    CANCELED_PAYMENT_FAILURE,
    WAITING,
    CANCELED_BY_USER,
    CANCELED_BY_SYSTEM  // Automatically canceled by the system 1 day before the trainStartDate if the ticket is not confirmed

}
