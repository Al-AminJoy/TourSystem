package com.alamin.toursystem.service;

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
    @Override
    public List<EventUser> getAll() {
        List<EventUser> list=new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public EventUser findById(long event_user_id) throws ResourceNotFoundException {
        EventUser result=repository.findById(event_user_id).orElseThrow(ResourceNotFoundException::new);
        return result;
    }

    @Override
    public List<EventUserModel> findUsersByEvent(long event_id) throws ResourceNotFoundException {
        List<EventUser> eventUsers=new ArrayList<>();
        repository.findByEvent(event_id).forEach(eventUsers::add);
        List<EventUserModel> eventUserModels=new ArrayList<>();
        for (EventUser eventUser:eventUsers){
            User user =userDao.findById(eventUser.getUser_id());
            eventUserModels.add(new EventUserModel(
                    eventUser.getEvent_id(),
                    user
            ));
        }
        return eventUserModels;
    }


    @Override
    public EventUser create(EventUser model) throws ResourceAlreadyExistException {
        if (repository.existsById(model.getEvent_user_id())){
            throw new ResourceAlreadyExistException();
        }
        else {
            EventUser created=repository.save(model);
            return created;
        }
    }

    @Override
    public EventUser update(EventUser model) throws ResourceNotFoundException {
       EventUser user=new EventUser(
               model.getEvent_user_id(),
               model.getEvent_id(),
               model.getUser_id()
       );
       if (repository.existsById(model.getEvent_user_id())){
           EventUser updated=repository.save(user);
           return updated;
       }
       else {
           throw new ResourceNotFoundException();
       }
    }

    @Override
    public EventUser deleteById(long event_user_id) throws ResourceNotFoundException {
        if (repository.existsById(event_user_id)){
            EventUser deleted=repository.findById(event_user_id).get();
            repository.deleteById(event_user_id);
            return deleted;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }
}
