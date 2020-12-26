package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.AgencyDao;
import com.alamin.toursystem.entity.Agency;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agency")
public class AgencyController {
    @Autowired
    private AgencyDao dao;
    /*@GetMapping("")
    public ResponseEntity<List<Agency>> readUsers() {
        return ResponseEntity.ok(dao.getAll());
    }

    @GetMapping("/{agency_id}")
    public ResponseEntity<Agency> readUser(@PathVariable long agency_id) {
        try {
            return ResponseEntity.ok(dao.findById(agency_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

     */
    @GetMapping("")
    public ResponseEntity<List<AgencyModel>> readUsers() {
        return ResponseEntity.ok(dao.getAllAgency());
    }

    @GetMapping("/{agency_id}")
    public ResponseEntity<AgencyModel> readUser(@PathVariable long agency_id) {
        try {
            return ResponseEntity.ok(dao.findByAgencyId(agency_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("")
    public ResponseEntity<Agency> createUser(@RequestBody Agency model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<Agency> updateUser(@RequestBody Agency model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
        } catch ( ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/{agency_id}")
    public ResponseEntity<Agency> deleteUser(@PathVariable long agency_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(agency_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
