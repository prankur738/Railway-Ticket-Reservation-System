package com.learning.bookingservice.model;

import com.learning.bookingservice.dto.request.PassengerDetail;
import com.learning.bookingservice.enums.BookingStatus;
import com.learning.bookingservice.enums.Gender;
import com.learning.bookingservice.enums.PaymentStatus;
import com.learning.bookingservice.enums.SeatType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Builder
@Table(name = "passenger")
@Setter @Getter
public class Passenger extends BaseModel{
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "seat_type")
    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @Column(name = "seat_number")
    private Integer seatNumber;

    @Column(name = "fare")
    private Float fare;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name = "payment_status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @ManyToOne
    @JoinColumn(name = "booking_details_id")
    private BookingDetails bookingDetails;

    public static Passenger from(PassengerDetail request, SeatType seatType, int journeyDistance, BookingDetails bookingDetails) {

        return Passenger.builder()
                .name(request.getName())
                .age(request.getAge())
                .gender(request.getGender())
                .seatType(seatType)
                .seatNumber(null)
                .fare(SeatType.SLEEPER.getFare(journeyDistance))
                .bookingStatus(BookingStatus.PAYMENT_PENDING)  // Payment is pending in book-ticket request
                .paymentStatus(PaymentStatus.PAYMENT_PENDING)
                .bookingDetails(bookingDetails)
                .build();
    }
}
