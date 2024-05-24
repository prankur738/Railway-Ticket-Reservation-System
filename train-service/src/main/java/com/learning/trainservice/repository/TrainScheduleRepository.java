package com.learning.trainservice.repository;

import com.learning.trainservice.dto.TrainDataDto;
import com.learning.trainservice.entity.TrainSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrainScheduleRepository extends JpaRepository<TrainSchedule, Long> {
    @Query("SELECT new com.learning.trainservice.dto.TrainDataDto(" +
            "t1.trainNumber, " +
            "t1.departureTime, t1.trainDay, " +
            "t2.arrivalTime, t2.trainDay, " +
            "t2.distanceFromOrigin-t1.distanceFromOrigin, " +
            ":stationId1, " +
            ":stationId2) " +
            "FROM TrainSchedule t1 " +
            "JOIN TrainSchedule t2 ON t1.trainNumber = t2.trainNumber " +
            "WHERE t1.stationCode = :stationId1 " +
            "AND t2.stationCode = :stationId2 " +
            "AND t1.departureTime IS NOT NULL " +
            "AND t2.arrivalTime IS NOT NULL " +
            "AND t1.distanceFromOrigin < t2.distanceFromOrigin")
    List<TrainDataDto> findTrainBetweenTwoStations(String stationId1, String stationId2);


}
