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
   /* @GetMapping("")
    public ResponseEntity<List<Event>> readReviews(){
        return ResponseEntity.ok(dao.getAll());
    }
    @GetMapping("/{event_id}")
    public ResponseEntity<Event> readReview(@PathVariable long event_id) {
        try {
            return ResponseEntity.ok(dao.findById(event_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    */
   @GetMapping("")
   public ResponseEntity<List<EventModel>> readReviews(){
       return ResponseEntity.ok(dao.getEvents());
   }
    @PostMapping("")
    public ResponseEntity<Event> createReview(@RequestBody Event model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("")
    public ResponseEntity<Event> updateReview(@RequestBody Event model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
        } catch ( ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }

}
