package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.EventUserDao;
import com.alamin.toursystem.entity.EventUser;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
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
    @GetMapping("")
    public ResponseEntity<List<EventUser>> readReviews(){
        return ResponseEntity.ok(dao.getAll());
    }
    @GetMapping("/{event_user_id}")
    public ResponseEntity<EventUser> readReview(@PathVariable long event_user_id) {
        try {
            return ResponseEntity.ok(dao.findById(event_user_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<EventUser> createReview(@RequestBody EventUser model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("")
    public ResponseEntity<EventUser> updateReview(@RequestBody EventUser model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
        } catch ( ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/{event_user_id}")
    public ResponseEntity<EventUser> deleteReview(@PathVariable long event_user_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(event_user_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
