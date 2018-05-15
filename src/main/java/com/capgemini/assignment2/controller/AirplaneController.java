package com.capgemini.assignment2.controller;

import com.capgemini.assignment2.exception.NotFoundException;
import com.capgemini.assignment2.models.Airplane;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.capgemini.assignment2.repository.AirplaneRepository;
import com.capgemini.assignment2.repository.AirportRepository;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/airplane/")
public class AirplaneController {

    private static ArrayList<Airplane> airplanes = new ArrayList<>();


    @Autowired
    private AirplaneRepository airplaneRepository;

    //Get all airplanes from the com.capgemini.assignment2.repository
    @RequestMapping(value= "/", method = RequestMethod.GET)
    public Iterable<Airplane> getAirplane() {
        return airplaneRepository.findAll();
    }

    //Get single airplane by ID
    @RequestMapping(value= "/{id}", method = RequestMethod.GET)
    public Airplane getAirplaneById(@PathVariable int id) {
        Optional<Airplane> airplane = airplaneRepository.findById(id);

        if (airplane.isPresent()) {
            return airplane.get();
        }
        return null;
    }

    //Save airplane to com.capgemini.assignment2.repository
    @RequestMapping(value ="/add", method =RequestMethod.POST)
    public Airplane registerAirplane(@RequestBody Airplane airplane) {
        airplanes.add(airplane);
        return airplaneRepository.save(airplane);
    }

//    //Delete airplane from com.capgemini.assignment2.repository. Give Exception when there is no such ID.
//    @RequestMapping(value = "delete/{id}", method = RequestMethod.DELETE)
//    public void deleteAirplane(@PathVariable long id) {
//        Airplane airplane = airplaneRepository.findOne(id);
//
//        if (airplane == null)
//            throw new NotFoundException();
//
//        airplaneRepository.delete(airplane);
//    }

}

