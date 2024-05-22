package com.learning.trainservice.service;

import com.learning.trainservice.dto.SeatDataDto;
import com.learning.trainservice.dto.TrainDataDto;
import com.learning.trainservice.dto.request.NewTrainRequest;
import com.learning.trainservice.dto.request.SearchTrainsRequest;
import com.learning.trainservice.dto.response.TrainResponseDto;
import com.learning.trainservice.dto.response.SearchTrainsResponseDto;
import com.learning.trainservice.entity.Station;
import com.learning.trainservice.entity.Train;
import com.learning.trainservice.enums.BookingStatus;
import com.learning.trainservice.enums.SeatType;
import com.learning.trainservice.exception.DuplicateEntityException;
import com.learning.trainservice.exception.ResourceNotFoundException;
import com.learning.trainservice.mapper.TrainMapper;
import com.learning.trainservice.repository.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TrainService {

    private TrainRepository trainRepository;
    private StationRepository stationRepository;
    private TrainScheduleRepository trainScheduleRepository;
    private TrainFrequencyRepository trainFrequencyRepository;
    private SeatBookingRepository seatBookingRepository;

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

    public SearchTrainsResponseDto getAllTrainsBetweenTwoStationsOnDate(SearchTrainsRequest request) {
        Station departureStation = stationRepository.findByStationCode(request.getFromStation()).orElseThrow(
                () -> new ResourceNotFoundException(request.getFromStation() + ": This station code doesn't exist.")
        );
        Station arrivalStation = stationRepository.findByStationCode(request.getToStation()).orElseThrow(
                () -> new ResourceNotFoundException(request.getToStation() + ": This station code doesn't exist.")
        );

        //Storing numeric value of day on departureDate Mon->0, Tue->1, etc.
        Integer passengerDepartureDayOfWeek = request.getDepartureDate().getDayOfWeek().getValue() -1 ;

        /** First, fetching trains running between the departure and arrival stations, then filter them based on their operation on the departure day */
        List<TrainDataDto> trainDataDtos = trainScheduleRepository.findTrainBetweenTwoStations(request.getFromStation(), request.getToStation())
                .stream()
                .filter(trainDataDto -> {
                    /** Filtering trains based on whether they operate/run on the day of departure */
                    Boolean isRunning = trainFrequencyRepository.findIsRunningByTrainNumberAndDepartureDayAndTrainDay(trainDataDto.getTrainNumber(), passengerDepartureDayOfWeek, trainDataDto.getDepartureTrainDay());
                    return !ObjectUtils.isEmpty(isRunning) && isRunning;
                })
                .toList();

        /**mapping the eligible-trains-data to response DTOs */
        List<TrainResponseDto> trains = trainDataDtos.stream()
                .map(trainDataDto -> {
                    String trainNumber = trainDataDto.getTrainNumber();

                    Optional<Train> train = trainRepository.findByTrainNumber(trainNumber);

                    if(train.isEmpty()) {
                        log.error("Train not found for trainNumber: {} while ", trainNumber);
                        return null;
                    }

                    TrainResponseDto trainResponseDto = TrainResponseDto.from(trainDataDto, train.get().getTrainName(), request.getDepartureDate());

                    try {
                        // Calculating trainStartDate from originating station using passenger departure date and trainDay on departure station
                        int daysToSubtract = trainDataDto.getDepartureTrainDay() - 1;
                        LocalDate trainStartDate = request.getDepartureDate().minusDays(daysToSubtract);

                        List<SeatDataDto> availableAcSeats = seatBookingRepository.findSeatsByTrainNumberAndTrainStartDateAndSeatTypeAndBookingStatus(trainNumber, trainStartDate, SeatType.AC, BookingStatus.NOT_BOOKED);
                        List<SeatDataDto> availableSleeperSeats = seatBookingRepository.findSeatsByTrainNumberAndTrainStartDateAndSeatTypeAndBookingStatus(trainNumber, trainStartDate, SeatType.SLEEPER, BookingStatus.NOT_BOOKED);

                        trainResponseDto.setAvailableAcSeats(availableAcSeats);
                        trainResponseDto.setAvailableSleeperSeats(availableSleeperSeats);
                    } catch (Exception e) {
                        log.error("Error fetching available seats for trainNumber: {}", trainNumber);
                        return null;
                    }

                    return trainResponseDto;

                })
                .filter(Objects::nonNull)
                .toList();


        return SearchTrainsResponseDto.builder()
                .fromStationCode(departureStation.getStationCode())
                .fromStationName(departureStation.getStationName())
                .toStationCode(arrivalStation.getStationCode())
                .toStationName(arrivalStation.getStationName())
                .departureDate(request.getDepartureDate())
                .trains(trains)
                .build();
    }
}
