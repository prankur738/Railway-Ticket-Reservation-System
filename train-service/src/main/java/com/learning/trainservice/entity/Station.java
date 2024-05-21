package com.learning.trainservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Station extends BaseModel {

    @Column(name = "station_code")
    private String stationCode;

    @Column(name = "station_name")
    private String stationName;
}
