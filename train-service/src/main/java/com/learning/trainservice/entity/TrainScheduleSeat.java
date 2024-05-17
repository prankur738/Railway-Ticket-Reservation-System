package com.learning.trainservice.entity;

import com.learning.trainservice.entity.enums.ScheduleSeatStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "train_schedule_seat")
public class TrainScheduleSeat extends BaseModel{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "train_schedule_id")
    private TrainSchedule trainSchedule;

    @ManyToOne
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(name = "schedule_seat_status")
    @Enumerated(value = EnumType.STRING)
    private ScheduleSeatStatus status;
}
