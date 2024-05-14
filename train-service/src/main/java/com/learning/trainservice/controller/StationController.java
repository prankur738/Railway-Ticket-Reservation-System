package com.learning.trainservice.controller;

import com.learning.trainservice.dto.response.ResponseDto;
import com.learning.trainservice.dto.request.NewStationRequest;
import com.learning.trainservice.service.StationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/station")
@AllArgsConstructor
public class StationController {

    private StationService stationService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addNewStation(@Valid @RequestBody NewStationRequest request) {
        stationService.addNewStation(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("201", "Station added successfully"));
    }

}
