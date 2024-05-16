package com.learning.trainservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "booking_seat")
public class BookingSeat extends BaseModel {
    @Column(name = "booking_id")
    private Long bookingId;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "pnr_id")
    private Long pnrId;
}
