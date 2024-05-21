package com.learning.trainservice.entity;

import com.learning.trainservice.entity.enums.CoachType;
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

    @Column(name = "coach_number")
    private String coachNumber;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Column(name = "coach_type")
    @Enumerated(value = EnumType.STRING)
    private CoachType coachType;

}
