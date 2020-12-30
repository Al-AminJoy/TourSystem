package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.EventUserDao;
import com.alamin.toursystem.entity.EventUser;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.EventUserModel;
import com.alamin.toursystem.model.LocationReviewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("event_users")
public class EventUserController {
    @Autowired
    private EventUserDao dao;
    /**
     *Takes event_id  as input and returns an object of EventUserModel
     */
    @GetMapping("/{event_id}")
    public ResponseEntity<List<EventUserModel>>readEventUsers(@PathVariable long event_id) {
        try {
            return ResponseEntity.ok(dao.findUsersByEvent(event_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }}

    /**
     *Takes EventUser object as input and returns an object of EventUser
     */
    @PostMapping("")
    public ResponseEntity<EventUser> createEventUser(@RequestBody EventUser model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getEvent_id()<=0||model.getUser_id()<=0){
                return ResponseEntity.badRequest().build();
            }
            else {
                return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
            }

        } catch (ResourceAlreadyExistException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     *Takes an EventUser with an event_user_id as input and returns an object of EventUser
     */
    @PutMapping("")
    public ResponseEntity<EventUser> updateEventUser(@RequestBody EventUser model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getEvent_user_id()<=0||model.getEvent_id()<=0||model.getUser_id()<=0){
                return ResponseEntity.badRequest().build();
            }
            else {
                return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
            }

        } catch ( ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }
    /**
     *Takes event_user_id  as input and returns an object of EventUser after deleted the row
     */
    @DeleteMapping("/{event_user_id}")
    public ResponseEntity<EventUser> deleteReview(@PathVariable long event_user_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(event_user_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
