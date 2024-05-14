package com.learning.trainservice.mapper;

import com.learning.trainservice.dto.request.NewStationRequest;
import com.learning.trainservice.entity.Station;

public class StationMapper {

    public static Station mapToStation(NewStationRequest request) {
        return Station.builder()
                .stationCode(request.getStationCode())
                .stationName(request.getStationName())
                .build();
    }
}
