package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.CancelRequestDao;
import com.alamin.toursystem.dao.EventDao;
import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.entity.CancelRequest;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.CancelRequestModel;
import com.alamin.toursystem.repository.CancelRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CancelRequestService implements CancelRequestDao {
    @Autowired
    private CancelRequestRepository repository;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EventDao eventDao;

    /**
     * Gets a list of CancelRequest
     */
    @Override
    public List<CancelRequest> getAll() {
        List<CancelRequest> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    /**
     * Gets an object of CancelRequest if exist by passing req_id
     */
    @Override
    public CancelRequest findById(long req_id) throws ResourceNotFoundException {
        CancelRequest result = repository.findById(req_id).orElseThrow(ResourceNotFoundException::new);
        return result;
    }

    /**
     * Gets CancelRequestModel object which includes request and user info by passing event_id
     */
    @Override
    public List<CancelRequestModel> getRequest(long event_id) throws ResourceNotFoundException {
        List<CancelRequest> list = new ArrayList<>();
        /**
         *Gets list of CancelRequest by Event
         */
        repository.findByEvent(event_id).forEach(list::add);
        List<CancelRequestModel> requestList = new ArrayList<>();
        for (CancelRequest request : list
        ) {
            requestList.add(new CancelRequestModel(
                    request.getCancel_req_id(),
                    request.getCancel_req_time(),
                    request.getEvent_id(),
                    userDao.findById(request.getUser_id())
            ));

        }

        return requestList;
    }

    /**
     * Creating CancelRequest
     */
    @Override
    public CancelRequest create(CancelRequest model) throws ResourceAlreadyExistException, ResourceNotFoundException {
        model.setCancel_req_accepted(false);
        /**
         *Checks Request exist or not by cancel_req_id
         * checks event exist or not by event_id from EventDao
         * checks user exist or not by user_id from UserDao
         */
        if (repository.existsById(model.getCancel_req_id())) {
            throw new ResourceAlreadyExistException();
        } else if (eventDao.findByExist(model.getEvent_id()) == false) {
            throw new ResourceNotFoundException();
        } else if (userDao.findByExist(model.getUser_id()) == false) {
            throw new ResourceNotFoundException();
        } else {
            CancelRequest created = repository.save(model);
            return created;
        }
    }

    /**
     * Updates CancelRequest
     */
    @Override
    public CancelRequest update(CancelRequest model) throws ResourceNotFoundException {
        CancelRequest request = new CancelRequest(
                model.getCancel_req_id(),
                model.getCancel_req_time(),
                model.getUser_id(),
                model.getEvent_id(),
                model.isCancel_req_accepted()
        );
        /**
         *Checks Request exist or not by cancel_req_id
         * checks event exist or not by event_id from EventDao
         * checks user exist or not by user_id from UserDao
         */
        if (repository.existsById(model.getCancel_req_id())) {
            if (eventDao.findByExist(model.getEvent_id()) == false) {
                throw new ResourceNotFoundException();
            } else if (userDao.findByExist(model.getUser_id()) == false) {
                throw new ResourceNotFoundException();
            } else {
                CancelRequest updated = repository.save(request);
                return updated;
            }

        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Deletes the row if it exist by req_id
     */
    @Override
    public CancelRequest deleteById(long req_id) throws ResourceNotFoundException {
        if (repository.existsById(req_id)) {
            CancelRequest deleted = repository.findById(req_id).get();
            repository.deleteById(req_id);
            return deleted;
        } else {
            throw new ResourceNotFoundException();
        }
    }


}
