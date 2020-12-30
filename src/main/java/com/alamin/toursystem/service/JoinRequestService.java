package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.EventDao;
import com.alamin.toursystem.dao.JoinRequestDao;
import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.entity.CancelRequest;
import com.alamin.toursystem.entity.JoinRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.JoinRequestModel;
import com.alamin.toursystem.repository.JoinRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JoinRequestService implements JoinRequestDao {
    @Autowired
    private JoinRequestRepository repository;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EventDao eventDao;
    @Override
    public List<JoinRequest> getAll() {
        List<JoinRequest> list=new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }
    @Override
    public List<JoinRequestModel> getRequest(long event_id) throws ResourceNotFoundException {
        List<JoinRequest> list=new ArrayList<>();
        repository.findByEvent(event_id).forEach(list::add);
        List<JoinRequestModel> requestList=new ArrayList<>();
        for (JoinRequest request:list
        ) {
          requestList.add(new JoinRequestModel(
                    request.getJoin_req_id(),
                    request.getJoin_req_time(),
                    request.getEvent_id(),
                    userDao.findById(request.getUser_id())
            ));

        }

        return requestList;
    }
    @Override
    public JoinRequest findById(long req_id) throws ResourceNotFoundException {
        JoinRequest result=repository.findById(req_id).orElseThrow(ResourceNotFoundException::new);
        return result;
    }

    @Override
    public JoinRequest create(JoinRequest model) throws ResourceAlreadyExistException,ResourceNotFoundException {
        model.setJoin_req_accepted(false);
        if (repository.existsById(model.getJoin_req_id())){
            throw new ResourceAlreadyExistException();
        }
        else if (userDao.findByExist(model.getUser_id())==false){
            throw new ResourceNotFoundException();
        }
        else if (eventDao.findByExist(model.getEvent_id())==false){
            throw new ResourceNotFoundException();
        }
        else {
            JoinRequest created=repository.save(model);
            return created;
        }
    }

    @Override
    public JoinRequest update(JoinRequest model) throws ResourceNotFoundException {
        JoinRequest request=new JoinRequest(
                model.getJoin_req_id(),
                model.getJoin_req_time(),
                model.getUser_id(),
                model.getEvent_id(),
                model.isJoin_req_accepted()
        );
        if (repository.existsById(model.getJoin_req_id())){
            if (userDao.findByExist(model.getUser_id())==false){
                throw new ResourceNotFoundException();
            }
            else if (eventDao.findByExist(model.getEvent_id())==false){
                throw new ResourceNotFoundException();
            }
            else {
                JoinRequest updated=repository.save(request);
                return updated;
            }

        }
        else {
            throw new  ResourceNotFoundException();
        }
    }

    @Override
    public JoinRequest deleteById(long req_id) throws ResourceNotFoundException {
        if (repository.existsById(req_id)){
            JoinRequest deleted=repository.findById(req_id).get();
            repository.deleteById(req_id);
            return deleted;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }
}
