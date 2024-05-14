package com.learning.trainservice.mapper;

import com.learning.trainservice.dto.request.NewTrainRequest;
import com.learning.trainservice.entity.Train;

public class TrainMapper {

    public static Train mapToTrain(NewTrainRequest newTrainRequest) {
        return Train.builder()
                .trainName(newTrainRequest.getTrainName())
                .trainNumber(newTrainRequest.getTrainNumber())
//                .startDay(newTrainRequest.getStartDay())
                .endDay(newTrainRequest.getEndDay())
                .startStationCode(newTrainRequest.getStartStationCode())
                .endStationCode(newTrainRequest.getEndStationCode())
                .startTime(newTrainRequest.getStartTime())
                .endTime(newTrainRequest.getEndTime())
                .totalDistance(newTrainRequest.getTotalDistance())
                .build();
    }
}
