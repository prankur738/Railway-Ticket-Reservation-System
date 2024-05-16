package com.learning.trainservice.entity;

import com.learning.trainservice.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking extends BaseModel {
    // User to be added

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name = "booked_at")
    private Date bookedAt;

    @Column(name = "amount")
    private int amount;

    //Payments to be added

}
