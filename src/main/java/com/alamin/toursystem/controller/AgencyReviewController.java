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

    /**
     * Takes AgencyReview object as input and returns an object of AgencyReview
     */
    @PostMapping("")
    public ResponseEntity<AgencyReview> createReview(@RequestBody AgencyReview model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getAgency_rating() <= 0
                    || model.getAgency_id() <= 0
                    || model.getUser_id() <= 0) {
                return ResponseEntity.badRequest().build();
            } else {
                /**
                 *checking the column value sizes
                 */
                if (model.getAgency_rating() > 5 || model.getAgency_review_comment().length() > 255) {
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
     * Takes an AgencyReview with an agency_id as input and returns an object of AgencyReview
     */
    @PutMapping("")
    public ResponseEntity<AgencyReview> updateReview(@RequestBody AgencyReview model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getAgency_review_id() <= 0
                    || model.getAgency_rating() <= 0
                    || model.getAgency_id() <= 0
                    || model.getUser_id() <= 0) {
                return ResponseEntity.badRequest().build();
            } else {
                /**
                 *checking the column value sizes
                 */
                if (model.getAgency_rating() > 5 || model.getAgency_review_comment().length() > 255) {
                    return ResponseEntity.badRequest().build();
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
                }
            }

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }

    /**
     * Takes agency_review_id  as input and returns an object of AgencyReview after deleted the row
     */
    @DeleteMapping("/{agency_review_id}")
    public ResponseEntity<AgencyReview> deleteReview(@PathVariable long agency_review_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(agency_review_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
