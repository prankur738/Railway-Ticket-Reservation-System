package com.learning.trainservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
public class TrainFrequency extends BaseModel {

    @Column(name = "train_number")
    private String trainNumber;

    /** Numeric value of dayOfWeek
     /** Monday-0, Tuesday-1, ..... ,Saturday-5, Sunday-6 */
    @Column(name = "day_of_week")
    private Integer dayOfWeek;

    @Column(name = "is_running")
    private Boolean isRunning;
}
