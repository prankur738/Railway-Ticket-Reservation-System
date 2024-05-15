package com.learning.trainservice.repository;

import com.learning.trainservice.entity.TrainFrequency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainFrequencyRepository extends JpaRepository<TrainFrequency, Long> {

    @Query("SELECT tf.isRunning FROM TrainFrequency tf " +
            "WHERE tf.trainNumber = :trainNumber AND " +
            ":departureDayOfWeek = ( (tf.dayOfWeek + :trainDay - 1) % 7 )")
    Boolean findIsRunningByTrainNumberAndDayOfWeek(@Param("trainNumber") String trainNumber,
                                                   @Param("departureDayOfWeek") Integer departureDayOfWeek,
                                                   @Param("trainDay") Integer trainDay);
}

