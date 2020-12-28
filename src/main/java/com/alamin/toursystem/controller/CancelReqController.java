package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.CancelRequestDao;
import com.alamin.toursystem.dao.JoinRequestDao;
import com.alamin.toursystem.entity.CancelRequest;
import com.alamin.toursystem.entity.JoinRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.CancelRequestModel;
import com.alamin.toursystem.model.JoinRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cancel_requests")
public class CancelReqController {
    @Autowired
    private CancelRequestDao dao;
    /*@GetMapping("")
    public ResponseEntity<List<CancelRequest>> readUsers() {
        return ResponseEntity.ok(dao.getAll());
    }

    @GetMapping("/{cancel_req_id}")
    public ResponseEntity<CancelRequest> readUser(@PathVariable long cancel_req_id) {
        try {
            return ResponseEntity.ok(dao.findById(cancel_req_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


     */
    @GetMapping("/{event_id}")
    public ResponseEntity<List<CancelRequestModel>> readUsers(@PathVariable long event_id ) {
        try {
            return ResponseEntity.ok(dao.getRequest(event_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping("")
    public ResponseEntity<CancelRequest> createUser(@RequestBody CancelRequest model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }



    @DeleteMapping("/{cancel_req_id}")
    public ResponseEntity<CancelRequest> deleteUser(@PathVariable long cancel_req_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(cancel_req_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
