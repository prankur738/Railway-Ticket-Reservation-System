package com.learning.trainservice.controller;

import com.learning.trainservice.dto.request.SearchTrainsRequest;
import com.learning.trainservice.dto.response.ResponseDto;
import com.learning.trainservice.dto.request.NewTrainRequest;
import com.learning.trainservice.dto.response.SearchTrainsResponseDto;
import com.learning.trainservice.service.TrainService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/train")
@AllArgsConstructor
public class TrainController {

    private TrainService trainService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addNewTrain(@Valid @RequestBody NewTrainRequest newTrainRequest) {
        trainService.addNewTrain(newTrainRequest);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201", "Train added successfully"));
    }

    /**
     * This api is fetching trains running between the stations on departureDate
     * along with their available ac and sleeper seats
     */
    @GetMapping("/search-trains")
    public ResponseEntity<SearchTrainsResponseDto> getTrainsBetweenTwoStations(@Valid @RequestBody SearchTrainsRequest request) {
        SearchTrainsResponseDto searchTrainsResponseDto = trainService.getAllTrainsBetweenTwoStationsOnDate(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(searchTrainsResponseDto);
    }
}
