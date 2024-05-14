package com.learning.trainservice.service;

import com.learning.trainservice.dto.TrainDataDto;
import com.learning.trainservice.dto.request.NewTrainRequest;
import com.learning.trainservice.dto.request.TrainSearchRequest;
import com.learning.trainservice.dto.response.TrainResponseDto;
import com.learning.trainservice.dto.response.TrainSearchResponseDto;
import com.learning.trainservice.entity.Station;
import com.learning.trainservice.entity.Train;
import com.learning.trainservice.exception.DuplicateEntityException;
import com.learning.trainservice.exception.ResourceNotFoundException;
import com.learning.trainservice.mapper.TrainMapper;
import com.learning.trainservice.repository.StationRepository;
import com.learning.trainservice.repository.TrainRepository;
import com.learning.trainservice.repository.TrainScheduleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TrainService {

    private TrainRepository trainRepository;
    private StationRepository stationRepository;
    private TrainScheduleRepository trainScheduleRepository;

    public void addNewTrain(NewTrainRequest request) {
        Optional<Train> existingTrain = trainRepository.findByTrainNumber(request.getTrainNumber());
        if(existingTrain.isPresent()) {
            throw new DuplicateEntityException("A train with the same train number: " + request.getTrainNumber() + "already exists.");
        }

        stationRepository.findByStationCode(request.getStartStationCode()).orElseThrow(
                () -> new ResourceNotFoundException(request.getStartStationCode() + ": This station code doesn't exist.")
        );
        stationRepository.findByStationCode(request.getEndStationCode()).orElseThrow(
                () -> new ResourceNotFoundException(request.getEndStationCode() + ": This station code doesn't exist.")
        );

        trainRepository.save(TrainMapper.mapToTrain(request));
    }

    public TrainSearchResponseDto getAllTrainsBetweenTwoStationsOnDate(TrainSearchRequest request) {
        List<TrainDataDto> trainDtos = trainScheduleRepository.findTrainBetweenTwoStations(request.getFromStation(), request.getToStation());

        Station departureStation = stationRepository.findByStationCode(request.getFromStation()).orElseThrow(
                () -> new ResourceNotFoundException(request.getFromStation() + ": This station code doesn't exist.")
        );
        Station arrivalStation = stationRepository.findByStationCode(request.getToStation()).orElseThrow(
                () -> new ResourceNotFoundException(request.getToStation() + ": This station code doesn't exist.")
        );


        List<TrainResponseDto> trains = trainDtos.stream()
                .map(trainDataDto -> {
                    String trainName = trainRepository.findByTrainNumber(trainDataDto.getTrainNumber()).get().getTrainName();
                    return TrainResponseDto.from(
                            trainDataDto,
                            trainName,
                            request.getDepartureDate()
                    );
                })
                .toList();

        return TrainSearchResponseDto.builder()
                .fromStationCode(departureStation.getStationCode())
                .fromStationName(departureStation.getStationName())
                .toStationCode(arrivalStation.getStationCode())
                .toStationName(arrivalStation.getStationName())
                .departureDate(request.getDepartureDate())
                .trains(trains)
                .build();
    }
}
