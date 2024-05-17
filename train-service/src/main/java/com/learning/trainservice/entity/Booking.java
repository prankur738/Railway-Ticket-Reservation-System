package com.learning.trainservice.entity;

import com.learning.trainservice.entity.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "booking")
public class Booking extends BaseModel {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "booking_status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @Column(name = "booked_at")
    private LocalDateTime bookedAt;

    @Column(name = "amount")
    private int amount;

    //Payments to be added

}
