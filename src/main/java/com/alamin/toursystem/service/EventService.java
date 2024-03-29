package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.AgencyDao;
import com.alamin.toursystem.dao.EventDao;
import com.alamin.toursystem.dao.LocationDao;
import com.alamin.toursystem.entity.Event;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyModel;
import com.alamin.toursystem.model.EventModel;
import com.alamin.toursystem.model.LocationModel;
import com.alamin.toursystem.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService implements EventDao {
    @Autowired
    private EventRepository repository;
    @Autowired
    private LocationDao locationDao;
    @Autowired
    private AgencyDao agencyDao;

    /**
     * Gets list of Event
     */
    @Override
    public List<Event> getAll() {
        List<Event> list = new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    /**
     * Checks the Event exist or not by event_id
     */
    @Override
    public boolean findByExist(long event_id) {
        if (repository.existsById(event_id)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets an object of Event which id passed as parameter
     */
    @Override
    public Event findById(long event_id) throws ResourceNotFoundException {
        Event result = repository.findById(event_id).orElseThrow(ResourceNotFoundException::new);
        return result;
    }

    /**
     * Gets list of EventModel
     */
    @Override
    public List<EventModel> getEvents() {
        List<Event> list;
        /**
         *Gets list of Event
         */
        list = getAll();
        List<EventModel> models = new ArrayList<>();
        for (Event event : list) {
            try {
                /**
                 *Gets object of LocationModel from LocationDao by passing location_id
                 */
                LocationModel location = locationDao.findByLocationId(event.getLocation_id());
                /**
                 *Gets an object of AgencyModel from AgencyDao by passing agency_id
                 */
                AgencyModel agency = agencyDao.findByAgencyId(event.getAgency_id());
                models.add(new EventModel(
                        event.getEvent_id(),
                        event.getPackage_cost(),
                        event.getPeople(),
                        event.getBordering_point(),
                        event.getEvent_date(),
                        event.getEvent_description(),
                        location,
                        agency
                ));
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }
        return models;
    }

    /**
     * Creating an Event
     */
    @Override
    public Event create(Event model) throws ResourceAlreadyExistException, ResourceNotFoundException {
        /**
         *checks its already exist or not
         * checks the location exist or not
         * checks the agency exist or not
         */
        if (repository.existsById(model.getEvent_id())) {
            throw new ResourceAlreadyExistException();
        } else if (locationDao.findByExist(model.getLocation_id()) == false) {
            throw new ResourceNotFoundException();
        } else if (agencyDao.findByExist(model.getAgency_id()) == false) {
            throw new ResourceNotFoundException();
        } else {
            Event created = repository.save(model);
            return created;
        }
    }

    /**
     * Updates an Event
     */
    @Override
    public Event update(Event model) throws ResourceNotFoundException {
        /**
         *checks its already exist or not
         * checks the location exist or not
         * checks the agency exist or not
         */
        if (repository.existsById(model.getEvent_id())) {
            if (locationDao.findByExist(model.getLocation_id()) == false) {
                throw new ResourceNotFoundException();
            } else if (agencyDao.findByExist(model.getAgency_id()) == false) {
                throw new ResourceNotFoundException();
            } else {
                Event updated = repository.save(model);
                return updated;
            }

        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Deleted an Event if it exist vy event_id
     */
    @Override
    public Event deleteById(long event_id) throws ResourceNotFoundException {
        if (repository.existsById(event_id)) {
            Event deleted = repository.findById(event_id).get();
            repository.deleteById(event_id);
            return deleted;
        } else {
            throw new ResourceNotFoundException();
        }
    }


}
