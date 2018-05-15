package com.capgemini.assignment2.controller;

import com.capgemini.assignment2.models.Airport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.capgemini.assignment2.repository.AirportRepository;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/airport")
public class AirportController {

    private static ArrayList<Airport> airports = new ArrayList<>();


    @Autowired
    private AirportRepository airportRepository;

    //Get all airplanes from the com.capgemini.assignment2.repository
    @RequestMapping(value ="/", method = RequestMethod.GET)
    public Iterable<Airport> getAirport() {
        return airportRepository.findAll();
    }

    //Get single airplane by ID
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Airport getAirportByID(@PathVariable int id) {
        Optional<Airport> airport = airportRepository.findById(id);

        if (airport.isPresent()) {
            return airport.get();
        }
        return null;
    }
    //Save airplane to com.capgemini.assignment2.repository
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Airport registerAirport(@RequestBody Airport airport) {
        airports.add(airport);
        return airportRepository.save(airport);
    }

}
