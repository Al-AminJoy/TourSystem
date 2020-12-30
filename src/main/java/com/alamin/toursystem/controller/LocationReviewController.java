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
    /**
     *Takes LocationReview object as input and returns an object of LocationReview
     */
    @PostMapping("")
    public ResponseEntity<LocationReview> createReview(@RequestBody LocationReview model) {
        try {
            if(model.getLocation_rating()<=0||model.getLocation_id()<=0||model.getUser_id()<=0){
                return ResponseEntity.badRequest().build();
            }
            else {
                /**
                 *checking the column value sizes
                 */
                if (model.getLocation_rating()>5||model.getLocation_review_comment().length()>255){
                    return ResponseEntity.badRequest().build();
                }
                else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
                }
            }

        } catch (ResourceAlreadyExistException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     *Takes an LocationReview with an location_review_id as input and returns an object of LocationReview
     */
    @PutMapping("")
    public ResponseEntity<LocationReview> updateReview(@RequestBody LocationReview model) {
        try {
            /**
             *Does not allow null value as input
             */
            if(model.getLocation_review_id()<=0
                    ||model.getLocation_rating()<=0
                    ||model.getLocation_id()<=0
                    ||model.getUser_id()<=0){
                return ResponseEntity.badRequest().build();
            }
            else {
                /**
                 *checking the column value sizes
                 */
                if (model.getLocation_rating()>5
                        ||model.getLocation_review_comment().length()>255){
                    return ResponseEntity.badRequest().build();
                }
                else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
                }
            }

        } catch ( ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }
    /**
     *Takes location_review_id  as input and returns an object of LocationReview after deleted the row
     */
    @DeleteMapping("/{location_review_id}")
    public ResponseEntity<LocationReview> deleteReview(@PathVariable long location_review_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(location_review_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
