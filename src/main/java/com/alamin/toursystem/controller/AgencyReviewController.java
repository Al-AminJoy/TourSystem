package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.AgencyReviewDao;
import com.alamin.toursystem.entity.AgencyReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyReviewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/agency_reviews")
public class AgencyReviewController {
    @Autowired
    private AgencyReviewDao dao;
    @GetMapping("")
    public ResponseEntity<List<AgencyReview>> readReviews(){
        return ResponseEntity.ok(dao.getAll());
    }
    @GetMapping("/{agency_review_id}")
    public ResponseEntity<AgencyReview> readReview(@PathVariable long agency_review_id) {
        try {
            return ResponseEntity.ok(dao.findById(agency_review_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<AgencyReview> createReview(@RequestBody AgencyReview model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @PutMapping("")
    public ResponseEntity<AgencyReview> updateReview(@RequestBody AgencyReview model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
        } catch ( ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/{agency_review_id}")
    public ResponseEntity<AgencyReview> deleteReview(@PathVariable long agency_review_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(agency_review_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
