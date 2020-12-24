package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.Event;
import com.alamin.toursystem.entity.EventUser;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;

import java.util.List;

public interface EventUserDao {
    public List<EventUser> getAll();
    public EventUser findById(long event_user_id)throws ResourceNotFoundException;
    public EventUser create(EventUser model) throws ResourceAlreadyExistException;
    public EventUser update(EventUser model)throws ResourceNotFoundException;
    public EventUser deleteById(long event_user_id) throws ResourceNotFoundException;
}
