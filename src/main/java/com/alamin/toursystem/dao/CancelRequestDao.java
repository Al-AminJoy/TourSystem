package com.alamin.toursystem.dao;
import com.alamin.toursystem.entity.CancelRequest;
import com.alamin.toursystem.entity.JoinRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;

import java.util.List;

public interface CancelRequestDao {
    public List<CancelRequest> getAll();
    public CancelRequest findById(long req_id)throws ResourceNotFoundException;
    public CancelRequest create(CancelRequest model) throws ResourceAlreadyExistException;
    public CancelRequest update(CancelRequest model)throws ResourceNotFoundException;
    public CancelRequest deleteById(long req_id) throws ResourceNotFoundException;
}
