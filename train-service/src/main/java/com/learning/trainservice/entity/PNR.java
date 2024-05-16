package com.learning.trainservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "pnr")
public class PNR extends BaseModel {

    @Column(name = "pnr_number")
    private String pnrNumber;

    @Column(name = "seat_id")
    private Long seatId;

    @Column(name = "pnr_status")
    private String pnrStatus;
}
