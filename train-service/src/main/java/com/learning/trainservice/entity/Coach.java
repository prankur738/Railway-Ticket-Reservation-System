package com.learning.trainservice.entity;

import com.learning.trainservice.entity.enums.CoachType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coach")
public class Coach extends BaseModel {

    @Column(name = "coach_number")
    private String coachNumber;

    @ManyToOne
    @JoinColumn(name = "train_id")
    private Train train;

    @Column(name = "coach_type")
    @Enumerated(value = EnumType.STRING)
    private CoachType coachType;

}
