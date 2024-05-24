package com.learning.trainservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
@Table(name = "station")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "station_code")
    private String stationCode;

    @Column(name = "station_name")
    private String stationName;
}
