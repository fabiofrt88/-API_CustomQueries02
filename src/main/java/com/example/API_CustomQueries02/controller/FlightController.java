package com.example.API_CustomQueries02.controller;

import com.example.API_CustomQueries02.entities.Flight;
import com.example.API_CustomQueries02.entities.FlightStatus;
import com.example.API_CustomQueries02.entities.GeneratedClass;
import com.example.API_CustomQueries02.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightRepository flightRepository;

    @PostMapping("/postflights")
    public List<Flight> saveAllFlights(@RequestParam(required = false) Integer number) {

        List<Flight> flightListSave = new ArrayList<>();

        GeneratedClass genClass = new GeneratedClass();  //Genera la stringa random

        if (number == null) {
            number = 100;
        }

        for (int i = 0; i < number; i++) {
            flightListSave.add(new Flight(null, genClass.genString(), genClass.genString(), genClass.genString(), FlightStatus.values()[genClass.genInt()]));
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

    @GetMapping("/getsortfrom")
    public Page<Flight> getAllFight(@RequestParam(required = false) Optional<Integer> page, @RequestParam(required = false) Optional<Integer> size){
        if(page.isPresent() && size.isPresent()) {
            Sort sort = Sort.by(new Sort.Order(Sort.Direction.ASC,"fromAirport"));
            Pageable pageable = PageRequest.of(page.get(), size.get(), sort);
            Page<Flight> flights = flightRepository.findAll(pageable);
            return flights;
        }else {
            Page<Flight> pageFlights = Page.empty();
            return pageFlights;
        }
    }

    @GetMapping("/status/{status}")
    public List<Flight> getAllFlightsByStatus(@PathVariable FlightStatus status, @RequestParam int page, @RequestParam int size){
        return  flightRepository.findAllByStatus(status);
    }


    @GetMapping("/getqueryflight")
    public List<Flight> getFlightsByStatus(@RequestParam FlightStatus p1, @RequestParam FlightStatus p2){
        return flightRepository.getCustomFlight(p1, p2);
    }
}

