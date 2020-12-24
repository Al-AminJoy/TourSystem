package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.EventDao;
import com.alamin.toursystem.entity.Event;
import com.alamin.toursystem.entity.Location;
import com.alamin.toursystem.entity.Name;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EventService implements EventDao {
    @Autowired
    private EventRepository repository;
    @Override
    public List<Event> getAll() {
        List<Event> list=new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Event findById(long event_id) throws ResourceNotFoundException {
        Event result=repository.findById(event_id).orElseThrow(ResourceNotFoundException::new);
        return result;
    }

    @Override
    public Event create(Event model) throws ResourceAlreadyExistException {
        if (repository.existsById(model.getEvent_id())){
            throw new ResourceAlreadyExistException();
        }
        else {
            Event created=repository.save(model);
            return created;
        }
    }

    @Override
    public Event update(Event model) throws ResourceNotFoundException {
        if (repository.existsById(model.getEvent_id())){
            Event updated=repository.save(model);
            return updated;
        }
        else {
            throw  new ResourceNotFoundException();
        }
    }

    @Override
    public Event deleteById(long event_id) throws ResourceNotFoundException {
        return null;
    }
}
