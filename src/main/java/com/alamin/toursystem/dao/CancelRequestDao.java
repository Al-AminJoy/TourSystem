package com.alamin.toursystem.dao;
import com.alamin.toursystem.entity.CancelRequest;
import com.alamin.toursystem.entity.JoinRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.CancelRequestModel;
import com.alamin.toursystem.model.JoinRequestModel;

import java.util.List;

public interface CancelRequestDao {
     List<CancelRequest> getAll();
     CancelRequest findById(long req_id)throws ResourceNotFoundException;
     List<CancelRequestModel> getRequest(long event_id) throws ResourceNotFoundException ;
     CancelRequest create(CancelRequest model) throws ResourceAlreadyExistException, ResourceNotFoundException;
     CancelRequest update(CancelRequest model)throws ResourceNotFoundException;
     CancelRequest deleteById(long req_id) throws ResourceNotFoundException;

}
