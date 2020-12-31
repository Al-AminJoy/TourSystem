package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.EventDao;
import com.alamin.toursystem.dao.EventUserDao;
import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.entity.EventUser;
import com.alamin.toursystem.entity.User;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.EventUserModel;
import com.alamin.toursystem.repository.EventUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventUserService implements EventUserDao {
    @Autowired
    private EventUserRepository repository;
    @Autowired
    private UserDao userDao;
    @Autowired
    private EventDao eventDao;

    /**
     * Gets a List of EventUser
     */
    @Override
    public List<EventUser> getAll() {
        List<EventUser> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    /**
     * Gets an object of EventUser by passing event_user_id
     */
    @Override
    public EventUser findById(long event_user_id) throws ResourceNotFoundException {
        EventUser result = repository.findById(event_user_id).orElseThrow(ResourceNotFoundException::new);
        return result;
    }

    /**
     * Gets a List of EventUserModel
     */
    @Override
    public List<EventUserModel> findUsersByEvent(long event_id) throws ResourceNotFoundException {
        /**
         *Gets a List of EventUser
         */
        List<EventUser> eventUsers = getAll();
        List<EventUserModel> eventUserModels = new ArrayList<>();
        for (EventUser eventUser : eventUsers) {
            /**
             *Gets an object of User from UserDao by passing user_id
             */
            User user = userDao.findById(eventUser.getUser_id());
            eventUserModels.add(new EventUserModel(
                    eventUser.getEvent_user_id(),
                    eventUser.getEvent_id(),
                    user
            ));
        }
        return eventUserModels;
    }

    /**
     * Creates EventUser
     */
    @Override
    public EventUser create(EventUser model) throws ResourceAlreadyExistException, ResourceNotFoundException {
        /**
         *checks it previously exist or not
         * checks User exist or not
         * checks Event exist or not
         */
        if (repository.existsById(model.getEvent_user_id())) {
            throw new ResourceAlreadyExistException();
        } else if (userDao.findByExist(model.getUser_id()) == false) {
            throw new ResourceNotFoundException();
        } else if (eventDao.findByExist(model.getEvent_id()) == false) {
            throw new ResourceNotFoundException();
        } else {
            EventUser created = repository.save(model);
            return created;
        }
    }

    /**
     * Updates EventUser
     */
    @Override
    public EventUser update(EventUser model) throws ResourceNotFoundException {
        EventUser user = new EventUser(
                model.getEvent_user_id(),
                model.getEvent_id(),
                model.getUser_id()
        );
        /**
         *checks it previously exist or not
         * checks User exist or not
         * checks Event exist or not
         */
        if (repository.existsById(model.getEvent_user_id())) {
            if (userDao.findByExist(model.getUser_id()) == false) {
                throw new ResourceNotFoundException();
            } else if (eventDao.findByExist(model.getEvent_id()) == false) {
                throw new ResourceNotFoundException();
            } else {
                EventUser updated = repository.save(user);
                return updated;
            }

        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Deletes the row of EventUser if exist by event_user_id
     */
    @Override
    public EventUser deleteById(long event_user_id) throws ResourceNotFoundException {
        if (repository.existsById(event_user_id)) {
            EventUser deleted = repository.findById(event_user_id).get();
            repository.deleteById(event_user_id);
            return deleted;
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
