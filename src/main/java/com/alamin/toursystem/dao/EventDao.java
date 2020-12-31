package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.Event;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.EventModel;

import java.util.List;

public interface EventDao {
    List<Event> getAll();

    boolean findByExist(long event_id);

    Event findById(long event_id) throws ResourceNotFoundException;

    List<EventModel> getEvents();

    Event create(Event model) throws ResourceAlreadyExistException, ResourceNotFoundException;

    Event update(Event model) throws ResourceNotFoundException;

    Event deleteById(long event_id) throws ResourceNotFoundException;

}
