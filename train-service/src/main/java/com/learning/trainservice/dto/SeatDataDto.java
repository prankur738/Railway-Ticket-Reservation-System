package com.learning.trainservice.dto;

import com.learning.trainservice.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SeatDataDto {
    private Integer seatNumber;
    private SeatType seatType;
}
