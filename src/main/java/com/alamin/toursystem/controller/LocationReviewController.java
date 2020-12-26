package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.LocationReviewDao;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location_reviews")
public class LocationReviewController {
    @Autowired
    private LocationReviewDao dao;

    @GetMapping("")
    public ResponseEntity<List<LocationReview>> readReviews(){
        return ResponseEntity.ok(dao.getAll());
    }
    @GetMapping("/{location_review_id}")
    public ResponseEntity<LocationReview> readReview(@PathVariable long location_review_id) {
        try {
            return ResponseEntity.ok(dao.findById(location_review_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<LocationReview> createReview(@RequestBody LocationReview model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("")
    public ResponseEntity<LocationReview> updateReview(@RequestBody LocationReview model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
        } catch ( ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/{location_review_id}")
    public ResponseEntity<LocationReview> deleteReview(@PathVariable long location_review_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(location_review_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
