package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.Location;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.LocationModel;

import java.util.List;

public interface LocationDao {
    List<Location> getAll();

    boolean findByExist(long location_id);

    Location findById(long location_id) throws ResourceNotFoundException;

    LocationModel findByLocationId(long location_id) throws ResourceNotFoundException;

    List<LocationModel> getAllLocation();

    Location create(Location model) throws ResourceAlreadyExistException;

    Location update(Location model) throws ResourceNotFoundException;

    Location deleteById(long location_id) throws ResourceNotFoundException;

}
