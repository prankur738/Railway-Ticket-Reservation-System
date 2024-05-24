package com.learning.bookingservice.service;

import com.learning.bookingservice.dto.request.PassengerDetail;
import com.learning.bookingservice.dto.request.TicketBookingRequest;
import com.learning.bookingservice.dto.response.TicketBookingResponse;
import com.learning.bookingservice.enums.BookingStatus;
import com.learning.bookingservice.enums.PaymentStatus;
import com.learning.bookingservice.enums.SeatType;
import com.learning.bookingservice.model.BookingDetails;
import com.learning.bookingservice.model.Passenger;
import com.learning.bookingservice.repository.BookingDetailsRepository;
import com.learning.bookingservice.repository.PassengerRepository;
import com.learning.bookingservice.utils.IdGenerator;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class TicketBookingService {

    private BookingDetailsRepository bookingDetailsRepository;
    private PassengerRepository passengerRepository;

    public TicketBookingResponse bookTicket(TicketBookingRequest request) {

        // call to Train-service to fetch available seats by trainNumber and trainStartDate
        // and make status of those seats as AWAITING_PAYMENT in train-service
        List<Integer> availableSeats = new ArrayList<>();
        availableSeats.add(0);

        BookingDetails bookingDetails = bookingDetailsRepository.save(BookingDetails.from(request));

        // Prepare passengers list and set initial statuses
        List<Passenger> passengers = request.getPassengers()
                .stream()
                .map(passengerDetail -> Passenger.from(passengerDetail, request.getSeatType(), request.getJourneyDistanceInKms(), bookingDetails))
                .toList();

        passengers = passengerRepository.saveAll(passengers);

        // call to payment service
        // after payment confirmation allocate available seats to the passengers
        // Allocate seats and update statuses
        int index = 0;

        while (index < availableSeats.size()) {
            Passenger passenger = passengers.get(index);

            passenger.setSeatNumber(availableSeats.get(index));
            passenger.setBookingStatus(BookingStatus.CONFIRMED);
            passenger.setPaymentStatus(PaymentStatus.PAYMENT_DONE);

            index++;
        }

        while(index < passengers.size()) {
            Passenger passenger = passengers.get(index);

            passenger.setSeatNumber(null);
            passenger.setBookingStatus(BookingStatus.WAITING);
            passenger.setPaymentStatus(PaymentStatus.PAYMENT_DONE);

            index++;
        }


        passengers = passengerRepository.saveAll(passengers);

        return TicketBookingResponse.from(bookingDetails, passengers);
    }
}
