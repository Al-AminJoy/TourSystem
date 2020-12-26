package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.LocationDao;
import com.alamin.toursystem.entity.Location;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.LocationModel;
import com.alamin.toursystem.model.LocationReviewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    @Autowired
    private LocationDao dao;

    /**
     * Disabled Codes for Testing purpose for this table.
     */
/*
    @GetMapping("")
    public ResponseEntity<List<Location>> readLocations(){
        return ResponseEntity.ok(dao.getAll());
    }
    @GetMapping("/{location_id}")
    public ResponseEntity<Location> readLocation(@PathVariable long location_id) {
        try {
            return ResponseEntity.ok(dao.findById(location_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

     */
    @GetMapping("")
    public ResponseEntity<List<LocationModel>> readLocations(){
        return ResponseEntity.ok(dao.getAllLocation());
    }
    @GetMapping("/{location_id}")
    public ResponseEntity<LocationModel> readLocation(@PathVariable long location_id) {
        try {
            return ResponseEntity.ok(dao.findByLocationId(location_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("")
    public ResponseEntity<Location> createReview(@RequestBody Location model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
