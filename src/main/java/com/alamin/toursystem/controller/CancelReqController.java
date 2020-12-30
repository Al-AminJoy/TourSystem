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
    /**
     *Takes event_id  as input and returns an object of CancelRequestModel
     */
    @GetMapping("/{event_id}")
    public ResponseEntity<List<CancelRequestModel>> readReviews(@PathVariable long event_id ) {
        try {
            return ResponseEntity.ok(dao.getRequest(event_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    /**
     *Takes CancelReques object as input and returns an object of CancelRequest
     */
    @PostMapping("")
    public ResponseEntity<CancelRequest> createRequest(@RequestBody CancelRequest model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getCancel_req_time()==null
                    ||model.getUser_id()<=0
                    ||model.getEvent_id()<=0){
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
     *Takes cancel_req_id  as input and returns an object of AgencyReview after deleted the row
     */
    @DeleteMapping("/{cancel_req_id}")
    public ResponseEntity<CancelRequest> deleteRequest(@PathVariable long cancel_req_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(cancel_req_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
