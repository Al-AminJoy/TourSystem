package com.alamin.toursystem.dao;
import com.alamin.toursystem.entity.JoinRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.EventRequest;
import com.alamin.toursystem.repository.JoinRequestRepository;
import com.alamin.toursystem.service.JoinRequestService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public interface JoinRequestDao {

    public List<JoinRequest> getAll();
    public JoinRequest findById(long req_id)throws ResourceNotFoundException;
    public JoinRequest create(JoinRequest model) throws ResourceAlreadyExistException;
    public JoinRequest update(JoinRequest model)throws ResourceNotFoundException;
    public JoinRequest deleteById(long req_id) throws ResourceNotFoundException;
    public   List<EventRequest> getRequest(long event_id) throws ResourceNotFoundException ;
}
