package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.JoinRequestDao;
import com.alamin.toursystem.entity.JoinRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.JoinRequestModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("join_requests")
public class JoinReqController {
    @Autowired
    private JoinRequestDao dao;
    /**
     *Takes event_id  as input and returns an object of JoinRequestModel
     */
@GetMapping("/{event_id}")
public ResponseEntity<List<JoinRequestModel>> readRequests(@PathVariable long event_id ) {
    try {
        return ResponseEntity.ok(dao.getRequest(event_id));
    } catch (ResourceNotFoundException e) {
        return ResponseEntity.notFound().build();
    }
}
    /**
     *Takes JoinRequest object as input and returns an object of JoinRequest
     */
    @PostMapping("")
    public ResponseEntity<JoinRequest> createRequest(@RequestBody JoinRequest model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getJoin_req_time()==null||model.getUser_id()<=0||model.getEvent_id()<=0){
                return ResponseEntity.badRequest().build();
            }
            else {
                return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
            }

        } catch (ResourceAlreadyExistException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        }
    }
    /**
     *Takes join_req_id  as input and returns an object of JoinRequest after deleted the row
     */
    @DeleteMapping("/{join_req_id}")
    public ResponseEntity<JoinRequest> deleteRequest(@PathVariable long join_req_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(join_req_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
