package com.learning.bookingservice.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.learning.bookingservice.enums.SeatType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class TicketBookingRequest {
    private String trainNumber;

    private String fromStation; //departure-station-code

    private String toStation;    //arrival-station-code

    private LocalDateTime departureTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime arrivalTime;

    private int journeyDistanceInKms;

    private LocalDate trainStartDate;

    private SeatType seatType;

    private List<PassengerDetail> passengers;
}
