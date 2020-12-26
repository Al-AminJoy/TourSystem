package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.CancelRequestDao;
import com.alamin.toursystem.dao.JoinRequestDao;
import com.alamin.toursystem.entity.CancelRequest;
import com.alamin.toursystem.entity.JoinRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.repository.CancelRequestRepository;
import com.alamin.toursystem.repository.JoinRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CancelRequestService implements CancelRequestDao {
    @Autowired
    private CancelRequestRepository repository;
    @Override
    public List<CancelRequest> getAll() {
        List<CancelRequest> list=new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public CancelRequest findById(long req_id) throws ResourceNotFoundException {
        CancelRequest result=repository.findById(req_id).orElseThrow(ResourceNotFoundException::new);
        return result;
    }

    @Override
    public CancelRequest create(CancelRequest model) throws ResourceAlreadyExistException {
        model.setCancel_req_accepted(false);
        if (repository.existsById(model.getCancel_req_id())){
            throw new ResourceAlreadyExistException();
        }
        else {
            CancelRequest created=repository.save(model);
            return created;
        }
    }

    @Override
    public CancelRequest update(CancelRequest model) throws ResourceNotFoundException {
        return null;
    }

    @Override
    public CancelRequest deleteById(long req_id) throws ResourceNotFoundException {
        if (repository.existsById(req_id)){
            CancelRequest deleted=repository.findById(req_id).get();
            repository.deleteById(req_id);
            return deleted;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }
}
