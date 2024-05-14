package com.learning.trainservice.service;

import com.learning.trainservice.dto.request.NewStationRequest;
import com.learning.trainservice.entity.Station;
import com.learning.trainservice.exception.DuplicateEntityException;
import com.learning.trainservice.mapper.StationMapper;
import com.learning.trainservice.repository.StationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StationService {

    private StationRepository stationRepository;

    public void addNewStation(NewStationRequest request) {
        Optional<Station> existingStation = stationRepository.findByStationCode(request.getStationCode());
        if(existingStation.isPresent()) {
            throw new DuplicateEntityException("Station already exists with station code: " + request.getStationCode());
        }

        stationRepository.save(StationMapper.mapToStation(request));
    }
}
