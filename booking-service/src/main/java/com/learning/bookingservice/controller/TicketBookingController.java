package com.learning.bookingservice.controller;

import com.learning.bookingservice.dto.request.TicketBookingRequest;
import com.learning.bookingservice.dto.response.TicketBookingResponse;
import com.learning.bookingservice.service.TicketBookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TicketBookingController {
    private TicketBookingService ticketBookingService;

    @PostMapping("/book-ticket")
    public ResponseEntity<TicketBookingResponse> doTicketBooking(@RequestBody TicketBookingRequest request) {
        TicketBookingResponse ticketBookingResponse = ticketBookingService.bookTicket(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ticketBookingResponse);
    }
}
