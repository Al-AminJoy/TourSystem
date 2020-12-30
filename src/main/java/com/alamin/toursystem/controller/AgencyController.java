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
    /**
     *Takes agency_id as input and returns an object of Agency with its review
     */
    @GetMapping("/{agency_id}")
    public ResponseEntity<AgencyModel> readAgency(@PathVariable long agency_id) {
        try {
            return ResponseEntity.ok(dao.findByAgencyId(agency_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     *Takes Agency object as input and returns an object of Agency
     */
    @PostMapping("")
    public ResponseEntity<Agency> createAgency(@RequestBody Agency model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getAgency_name() == null
                    ||model.getAgency_address() == null
                    ||model.getAgency_email() == null
                    ||model.getAgency_primary_num() == null){
                return ResponseEntity.badRequest().build();
            }
            else {
                /**
                 *checking the column value sizes
                 */
                if ((model.getAgency_name().length() > 32)
                        && (model.getAgency_address().length() > 64)
                        && (model.getAgency_email().length() > 32)
                        && (model.getAgency_primary_num().length() > 11)
                        && (model.getAgency_number1().length() > 11)
                        && (model.getAgency_number2().length() > 11)) {
                    return ResponseEntity.badRequest().build();
                } else {

                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
                }
            }

        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     *Takes an Agency with an agency_id as input and returns an object of Agency
     */
    @PutMapping("")
    public ResponseEntity<Agency> updateAgency(@RequestBody Agency model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getAgency_id()<=0
                    ||model.getAgency_name() == null
                    ||model.getAgency_address() == null
                    ||model.getAgency_email() == null
                    ||model.getAgency_primary_num() == null){
                return ResponseEntity.badRequest().build();
            }
            else {
                /**
                 *Checking column value sizes
                 */
                if ((model.getAgency_name().length() > 32)
                        && (model.getAgency_address().length() > 64)
                        && (model.getAgency_email().length() > 32)
                        && (model.getAgency_primary_num().length() > 11)
                        && (model.getAgency_number1().length() > 11)
                        && (model.getAgency_number2().length() > 11)) {
                    return ResponseEntity.badRequest().build();

                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
                }
            }

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }
}
