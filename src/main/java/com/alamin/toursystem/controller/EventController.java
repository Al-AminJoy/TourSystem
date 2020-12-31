package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.EventDao;
import com.alamin.toursystem.entity.Event;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.EventModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@RestController
@RequestMapping("/events")
public class EventController {
    @Autowired
    private EventDao dao;

    /**
     * Provides a List of EventModel which includes Event and all details about Event
     */
    @GetMapping("")
    public ResponseEntity<List<EventModel>> readEvents() {
        return ResponseEntity.ok(dao.getEvents());
    }

    /**
     * Takes Event object as input and returns an object of Event
     */
    @PostMapping("")
    public ResponseEntity<Event> createEvents(@RequestBody Event model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getPackage_cost() <= 0
                    || model.getPeople() <= 0
                    || model.getBordering_point() == null
                    || model.getEvent_date() == null
                    || model.getLocation_id() <= 0
                    || model.getAgency_id() <= 0) {
                return ResponseEntity.badRequest().build();
            } else {
                /**
                 *checking the column value sizes
                 */
                if (model.getPackage_cost() > 999999999
                        || model.getPeople() > 999
                        || model.getBordering_point().length() > 32
                ) {
                    return ResponseEntity.badRequest().build();
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
                }
            }

        } catch (ResourceAlreadyExistException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Takes an Event with an event_id as input and returns an object of Event
     */
    @PutMapping("")
    public ResponseEntity<Event> updateEvents(@RequestBody Event model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getEvent_id() <= 0
                    || model.getPackage_cost() <= 0
                    || model.getPeople() <= 0
                    || model.getBordering_point() == null
                    || model.getEvent_date() == null
                    || model.getLocation_id() <= 0
                    || model.getAgency_id() <= 0) {
                return ResponseEntity.badRequest().build();
            } else {
                /**
                 *checking the column value sizes
                 */
                if (model.getPackage_cost() > 999999999
                        || model.getPeople() > 999
                        || model.getBordering_point().length() > 32
                ) {
                    return ResponseEntity.badRequest().build();
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
                }
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }

}
