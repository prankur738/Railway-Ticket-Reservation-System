package com.learning.trainservice.service;

import com.learning.trainservice.dto.request.NewTrainRequest;
import com.learning.trainservice.entity.Station;
import com.learning.trainservice.entity.Train;
import com.learning.trainservice.exception.DuplicateEntityException;
import com.learning.trainservice.exception.ResourceNotFoundException;
import com.learning.trainservice.mapper.TrainMapper;
import com.learning.trainservice.repository.StationRepository;
import com.learning.trainservice.repository.TrainRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.lang.module.ResolutionException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TrainService {

    private TrainRepository trainRepository;
    private StationRepository stationRepository;

    public void addNewTrain(NewTrainRequest request) {
        Optional<Train> existingTrain = trainRepository.findByTrainNumber(request.getTrainNumber());
        if(existingTrain.isPresent()) {
            throw new DuplicateEntityException("A train with the same train number: " + request.getTrainNumber() + "already exists.");
        }

        Station station1 = stationRepository.findByStationCode(request.getStartStationCode()).orElseThrow(
                () -> new ResourceNotFoundException(request.getStartStationCode() + ": This station code doesn't exist.")
        );
        Station station2 = stationRepository.findByStationCode(request.getEndStationCode()).orElseThrow(
                () -> new ResourceNotFoundException(request.getEndStationCode() + ": This station code doesn't exist.")
        );

        trainRepository.save(TrainMapper.mapToTrain(request));
    }
}
