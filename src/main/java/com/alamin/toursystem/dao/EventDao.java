package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.Event;
import com.alamin.toursystem.entity.Location;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.LocationModel;

import java.util.List;

public interface EventDao {
    public List<Event> getAll();
    public Event findById(long event_id)throws ResourceNotFoundException;
    public Event create(Event model) throws ResourceAlreadyExistException;
    public Event update(Event model)throws ResourceNotFoundException;
    public Event deleteById(long event_id) throws ResourceNotFoundException;
}
