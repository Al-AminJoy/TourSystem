package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.LocationDao;
import com.alamin.toursystem.entity.Location;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.LocationModel;
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
     * Provides a List of LocationModel Which Includes Location and LocationReview
     */
    @GetMapping("")
    public ResponseEntity<List<LocationModel>> readLocations() {
        return ResponseEntity.ok(dao.getAllLocation());
    }

    /**
     * Takes location_id  as input and returns an object of LocationModel
     */
    @GetMapping("/{location_id}")
    public ResponseEntity<LocationModel> readLocation(@PathVariable long location_id) {
        try {
            return ResponseEntity.ok(dao.findByLocationId(location_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Takes Location object as input and returns an object of Location
     */
    @PostMapping("")
    public ResponseEntity<Location> createLocation(@RequestBody Location model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getLocation_name() == null) {
                return ResponseEntity.badRequest().build();
            } else {
                /**
                 *checking the column value sizes
                 */
                if (model.getLocation_name().length() > 32) {
                    return ResponseEntity.badRequest().build();
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
                }
            }

        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
