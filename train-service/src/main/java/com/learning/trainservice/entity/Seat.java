package com.learning.trainservice.entity;

import com.learning.trainservice.entity.enums.SeatStatus;
import com.learning.trainservice.entity.enums.SeatType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "seat")
public class Seat extends BaseModel {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_schedule_id")
    private TrainSchedule trainSchedule;

    @Column(name = "seat_number")
    private Long seatNumber;

    @Column(name = "seat_type")
    @Enumerated(value = EnumType.STRING)
    private SeatType seatType;

    @Column(name = "coach")
    private String coach;

    @Column(name = "status")
    @Enumerated(value = EnumType.STRING)
    private SeatStatus status;
}
