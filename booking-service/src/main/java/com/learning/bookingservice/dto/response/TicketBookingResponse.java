package com.learning.bookingservice.dto.response;

import com.learning.bookingservice.enums.PaymentStatus;
import com.learning.bookingservice.model.BookingDetails;
import com.learning.bookingservice.model.Passenger;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public class TicketBookingResponse extends ResponseDto{
    private String trainNumber;

    private String fromStation; //departure-station-code

    private String toStation;    //arrival-station-code

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String pnr;

    private Float totalFare;

    private PaymentStatus paymentStatus;

    List<PassengerBookingResponse> passengers;

    public static TicketBookingResponse from(BookingDetails booking, List<Passenger> passengers) {
        List<PassengerBookingResponse> passengersListResponse = passengers.stream()
                .map(PassengerBookingResponse :: from)
                .toList();

        float totalFare = passengersListResponse.getFirst().getFare() * passengersListResponse.size();
        PaymentStatus paymentStatus = passengers.getFirst().getPaymentStatus();

        return TicketBookingResponse.builder()
                .trainNumber(booking.getTrainNumber())
                .fromStation(booking.getFromStation())
                .toStation(booking.getToStation())
                .departureTime(booking.getDepartureTime())
                .arrivalTime(booking.getArrivalTime())
                .pnr(booking.getPnr())
                .totalFare(totalFare)
                .paymentStatus(paymentStatus)
                .passengers(passengersListResponse)
                .build();

    }

}
