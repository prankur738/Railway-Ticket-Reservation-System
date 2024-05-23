package com.learning.trainservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learning.trainservice.dto.request.NewTrainRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
@WebMvcTest(controllers = TrainController.class)
class TrainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;


    private NewTrainRequest newTrainRequest = NewTrainRequest.builder()
            .trainNumber("11111")
            .trainName("Mongo Express")
            .endDay(2)
            .endStationCode("AED")
            .startStationCode("ADF")
            .endTime(LocalTime.parse("20:00:00"))
            .startTime(LocalTime.parse("23:00:00"))
            .totalDistance(2000)
            .build();

    @Test
    void addNewTrain() throws Exception{
        newTrainRequest.setTrainNumber("1234"); //Invalid trainNumber

        mockMvc.perform(post("/train/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newTrainRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Train number must be 5 digits"));
    }

//    @Test
//    void getTrainsBetweenTwoStations() {
//    }
}