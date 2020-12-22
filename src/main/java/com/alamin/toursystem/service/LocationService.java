package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.LocationDao;
import com.alamin.toursystem.entity.Location;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.LocationModel;
import com.alamin.toursystem.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class LocationService implements LocationDao {
    @Autowired
    private LocationRepository repository;
    @Override
    public List<Location> getAll() {
        List<Location> locationList=new ArrayList<>();
        repository.findAll().forEach(locationList::add);
        return locationList;
    }

    @Override
    public Location findById(long location_id) throws ResourceNotFoundException {
        Location review=repository.findById(location_id).orElseThrow(ResourceNotFoundException::new);
        return review;
    }

    @Override
    public Location create(LocationModel model) throws ResourceAlreadyExistException {
        Location location=new Location(model.getLocation_name());
        if (repository.existsById(model.getLocation_id())){
            throw new ResourceAlreadyExistException();
        }
        else {
            Location savedName=repository.save(location);
            return savedName;
        }
    }

    @Override
    public Location update(LocationModel model) throws ResourceNotFoundException {
        /*Location location=new Location(model.getLocation_id(),
                model.getLocation_name());
        if (repository.existsById(model.getLocation_id())){
            Location updatedLocation=repository.save(location);
            return updatedLocation;
        }
        else {
            throw  new ResourceNotFoundException();
        }

         */
        return null;
    }

    @Override
    public Location deleteById(long location_id) throws ResourceNotFoundException {
        /*if (repository.existsById(location_id)){
            Location deletedLocation=repository.findById(location_id).get();
            repository.deleteById(location_id);
            return deletedLocation;
        }
        else{
            throw new ResourceNotFoundException();
        }

         */
        return null;
    }
}
