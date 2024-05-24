package com.learning.bookingservice.dto.request;

import com.learning.bookingservice.enums.Gender;
import com.learning.bookingservice.enums.SeatType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class PassengerDetail {
    private String name;
    @Min(value = 4) @Max(value = 150)
    private int age;
    private Gender gender;
}
