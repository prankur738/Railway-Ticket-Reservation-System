package com.learning.trainservice.scheduler;

import com.learning.trainservice.entity.Seat;
import com.learning.trainservice.entity.SeatBooking;
import com.learning.trainservice.entity.Train;
import com.learning.trainservice.enums.BookingStatus;
import com.learning.trainservice.repository.SeatBookingRepository;
import com.learning.trainservice.repository.SeatRepository;
import com.learning.trainservice.repository.TrainFrequencyRepository;
import com.learning.trainservice.repository.TrainRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ScheduledTasks {
    private TrainRepository trainRepository;
    private TrainFrequencyRepository trainFrequencyRepository;
    private SeatRepository seatRepository;
    private SeatBookingRepository seatBookingRepository;


    @Scheduled(cron = "0 33 16 * * ?") // 4:20 PM every day
    public void addSeatsForBookingToStart() {
        log.info("Scheduler started at {}", LocalDateTime.now());
        List<Train> trains = trainRepository.findAll();

        // creating NOT_BOOKED seats for trains running after 3 months from today date
         LocalDate trainStartDate = LocalDate.now().plusMonths(3);

        int trainStartWeekDay = trainStartDate.getDayOfWeek().getValue() - 1;

        // Fetching trains running on this particular date
        List<Train> runningTrains = trains.stream()
                .filter(train -> {
                    Boolean isRunning = trainFrequencyRepository.findIsRunningByTrainNumberAndDayOfWeek(train.getTrainNumber(), trainStartWeekDay);
                    return !ObjectUtils.isEmpty(isRunning) && isRunning;
                })
                .toList();

        //Creating entries of seats as NOT_BOOKED in SeatBooking entity for running trains
        runningTrains.forEach(train -> {
            String trainNumber = train.getTrainNumber();
            log.info("Adding seats for trainNumber: {}", trainNumber);

            List<Seat> seats = seatRepository.findByTrainNumber(trainNumber);

            List<SeatBooking> seatBookingList = seats.stream()
                    .map(seat -> SeatBooking.builder()
                            .from(null)
                            .to(null)
                            .bookingId(null)
                            .seatNumber(seat.getSeatNumber())
                            .seatType(seat.getSeatType())
                            .trainNumber(trainNumber)
                            .trainStartDate(trainStartDate)
                            .bookingStatus(BookingStatus.NOT_BOOKED)
                            .build())
                    .toList();

            try {
                seatBookingRepository.saveAll(seatBookingList);
                log.info("{} seats added for trainNumber {}", seatBookingList.size(), trainNumber);
            } catch (Exception e) {
                log.error("Exception occurred while saving FRESH NOT_BOOKED seats in db: {}", e.getMessage());
            }
        });
    }
}
