package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.JoinRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.JoinRequestModel;

import java.util.List;

public interface JoinRequestDao {

    List<JoinRequest> getAll();

    JoinRequest findById(long req_id) throws ResourceNotFoundException;

    List<JoinRequestModel> getRequest(long event_id) throws ResourceNotFoundException;

    JoinRequest create(JoinRequest model) throws ResourceAlreadyExistException, ResourceNotFoundException;

    JoinRequest update(JoinRequest model) throws ResourceNotFoundException;

    JoinRequest deleteById(long req_id) throws ResourceNotFoundException;

}
