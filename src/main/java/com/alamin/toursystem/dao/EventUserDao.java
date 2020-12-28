package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.Event;
import com.alamin.toursystem.entity.EventUser;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.EventUserModel;

import java.util.List;

public interface EventUserDao {
     List<EventUser> getAll();
     EventUser findById(long event_user_id)throws ResourceNotFoundException;
     List<EventUserModel> findUsersByEvent(long event_id) throws ResourceNotFoundException;
     EventUser create(EventUser model) throws ResourceAlreadyExistException;
     EventUser update(EventUser model)throws ResourceNotFoundException;
     EventUser deleteById(long event_user_id) throws ResourceNotFoundException;
}
