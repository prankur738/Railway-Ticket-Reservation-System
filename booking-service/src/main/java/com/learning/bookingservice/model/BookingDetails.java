package com.learning.bookingservice.model;

import com.learning.bookingservice.dto.request.TicketBookingRequest;
import com.learning.bookingservice.utils.IdGenerator;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Builder @Getter
@Table(name = "booking_details")
public class BookingDetails extends BaseModel{

    @Column(name = "booking_id")
    private String bookingId;

    @Column(name = "pnr")
    private String pnr;

    @Column(name = "from_station")
    private String fromStation; //departure-station-code

    @Column(name = "to_station")
    private String toStation;    //arrival-station-code

    @Column(name = "train_number")
    private String trainNumber;

    @Column(name = "departure_time")
    private LocalDateTime departureTime;

    @Column(name = "arrival_time")
    private LocalDateTime arrivalTime;

    @Column(name = "journey_distance_in_kms")
    private Integer journeyDistanceInKms;

    @Column(name = "train_start_date")
    private LocalDate trainStartDate; // date on train's originating station

    //logged-in user whoever has booked the ticket
    @Column(name = "booked_by")
    private String bookedBy;

    @OneToMany(mappedBy = "bookingDetails")
    private List<Passenger> passengers;

    public static BookingDetails from(TicketBookingRequest request) {
        return BookingDetails.builder()
                .bookingId(IdGenerator.generateBookingId(request.getTrainNumber(), request.getDepartureTime()))
                .pnr(IdGenerator.generatePNR())
                .fromStation(request.getFromStation())
                .toStation(request.getToStation())
                .trainNumber(request.getTrainNumber())
                .departureTime(request.getDepartureTime())
                .arrivalTime(request.getArrivalTime())
                .journeyDistanceInKms(request.getJourneyDistanceInKms())
                .trainStartDate(request.getTrainStartDate())
                .bookedBy(null)
                .build();

    }
}
