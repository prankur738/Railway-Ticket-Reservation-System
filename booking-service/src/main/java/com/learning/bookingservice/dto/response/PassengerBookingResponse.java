package com.learning.bookingservice.dto.response;

import com.learning.bookingservice.enums.BookingStatus;
import com.learning.bookingservice.enums.Gender;
import com.learning.bookingservice.enums.SeatType;
import com.learning.bookingservice.model.Passenger;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;

@Builder @Getter
public class PassengerBookingResponse {
    private String name;
    @Min(value = 4) @Max(value = 150)
    private int age;
    private Gender gender;
    private BookingStatus bookingStatus;
    private Integer seatNumber;
    private SeatType seatType;
    private Float fare;

    public static PassengerBookingResponse from(Passenger passenger) {
        return PassengerBookingResponse.builder()
                .name(passenger.getName())
                .age(passenger.getAge())
                .gender(passenger.getGender())
                .bookingStatus(passenger.getBookingStatus())
                .seatNumber(passenger.getSeatNumber())
                .seatType(passenger.getSeatType())
                .fare(passenger.getFare())
                .build();
    }
}
