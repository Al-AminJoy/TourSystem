package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.JoinRequestDao;
import com.alamin.toursystem.entity.JoinRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.EventRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//TODO:Shift GetMapping from this controller to Event Controller
@RestController
@RequestMapping("join_requests")
public class JoinReqController {
    @Autowired
    private JoinRequestDao dao;
/*
    @GetMapping("")
    public ResponseEntity<List<JoinRequest>> readUsers() {
        return ResponseEntity.ok(dao.getAll());
    }
 */
    @GetMapping("/{event_id}")
    public ResponseEntity<List<EventRequest>> readUsers(@PathVariable long event_id ) {
        try {
            return ResponseEntity.ok(dao.getRequest(event_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
   /* @GetMapping("/{join_req_id}")
    public ResponseEntity<JoinRequest> readUser(@PathVariable long join_req_id) {
        try {
            return ResponseEntity.ok(dao.findById(join_req_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    */
    @PostMapping("")
    public ResponseEntity<JoinRequest> createUser(@RequestBody JoinRequest model) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{join_req_id}")
    public ResponseEntity<JoinRequest> deleteUser(@PathVariable long join_req_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(join_req_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
