package com.learning.trainservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seat")
public class Seat extends BaseModel {

    @Column(name = "seat_number")
    private Integer seatNumber;

    @ManyToOne
    @JoinColumn (name = "coach_id")
    private Coach coach;

}
