package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.EventDao;
import com.alamin.toursystem.dao.JoinRequestDao;
import com.alamin.toursystem.dao.UserDao;
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

    /**
     * Gets list of JoinRequest
     */
    @Override
    public List<JoinRequest> getAll() {
        List<JoinRequest> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    /**
     * Gets list of JoinRequestModel
     */
    @Override
    public List<JoinRequestModel> getRequest(long event_id) throws ResourceNotFoundException {
        /**
         *Gets list of JoinRequest
         */
        List<JoinRequest> list = getAll();
        List<JoinRequestModel> requestList = new ArrayList<>();
        /**
         *Gets list of JoinRequestModel which includes JoinRequest and User
         */
        for (JoinRequest request : list
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

    /**
     * Gets an Object of JoinRequest if exist by passing req_id
     */
    @Override
    public JoinRequest findById(long req_id) throws ResourceNotFoundException {
        JoinRequest result = repository.findById(req_id).orElseThrow(ResourceNotFoundException::new);
        return result;
    }

    /**
     * Creates JoinRequest
     */
    @Override
    public JoinRequest create(JoinRequest model) throws ResourceAlreadyExistException, ResourceNotFoundException {
        model.setJoin_req_accepted(false);
        /**
         *checks the request was previously exist or not
         * checks the User exist or not from UserDao by passing user_id
         * checks the Event exist or not from EventDao by passing event_id
         */
        if (repository.existsById(model.getJoin_req_id())) {
            throw new ResourceAlreadyExistException();
        } else if (userDao.findByExist(model.getUser_id()) == false) {
            throw new ResourceNotFoundException();
        } else if (eventDao.findByExist(model.getEvent_id()) == false) {
            throw new ResourceNotFoundException();
        } else {
            JoinRequest created = repository.save(model);
            return created;
        }
    }

    /**
     * Updates JoinRequest
     */
    @Override
    public JoinRequest update(JoinRequest model) throws ResourceNotFoundException {
        JoinRequest request = new JoinRequest(
                model.getJoin_req_id(),
                model.getJoin_req_time(),
                model.getUser_id(),
                model.getEvent_id(),
                model.isJoin_req_accepted()
        );
        /**
         *checks the request was previously exist or not
         * checks the User exist or not from UserDao by passing user_id
         * checks the Event exist or not from EventDao by passing event_id
         */
        if (repository.existsById(model.getJoin_req_id())) {
            if (userDao.findByExist(model.getUser_id()) == false) {
                throw new ResourceNotFoundException();
            } else if (eventDao.findByExist(model.getEvent_id()) == false) {
                throw new ResourceNotFoundException();
            } else {
                JoinRequest updated = repository.save(request);
                return updated;
            }

        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Deletes JoinRequest by passing req_id if exist
     */
    @Override
    public JoinRequest deleteById(long req_id) throws ResourceNotFoundException {
        if (repository.existsById(req_id)) {
            JoinRequest deleted = repository.findById(req_id).get();
            repository.deleteById(req_id);
            return deleted;
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
