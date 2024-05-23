package com.learning.trainservice.dto.response;

import com.learning.trainservice.enums.SeatType;
import lombok.Data;

@Data
public class SeatDto {
    private Integer seatNumber;

    private SeatType seatType;
}
