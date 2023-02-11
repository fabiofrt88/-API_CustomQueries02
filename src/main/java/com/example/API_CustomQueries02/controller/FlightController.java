package com.example.API_CustomQueries02.controller;

import com.example.API_CustomQueries02.entities.Flight;
import com.example.API_CustomQueries02.entities.FlightStatus;
import com.example.API_CustomQueries02.entities.GeneratedString;
import com.example.API_CustomQueries02.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/postall")
    public List<Flight> saveAllFlights() {

        List<Flight> flightListSave = new ArrayList<>();

        GeneratedString genStr = new GeneratedString();  //Genera la stringa random

        for (int i = 0; i < 50; i++) {
            flightListSave.add(new Flight(null, genStr.genString(), genStr.genString(), genStr.genString(), FlightStatus.ONTIME));
        }

        for (Flight fly : flightListSave) {
            flightRepository.saveAndFlush(fly);
        }

        return flightListSave;
    }

    @GetMapping("/getallflight")
    public List <Flight> getAllFight(){
        List <Flight> flights = flightRepository.findAll();
        return flights;
    }
}