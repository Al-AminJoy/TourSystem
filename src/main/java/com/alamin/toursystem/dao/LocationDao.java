package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.Location;
import com.alamin.toursystem.entity.Name;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.LocationModel;

import java.util.List;

public interface LocationDao {
    public List<Location> getAll();
    public Location findById(long location_id)throws ResourceNotFoundException;
    public Location create(LocationModel model) throws ResourceAlreadyExistException;
    public Location update(LocationModel model)throws ResourceNotFoundException;
    public Location deleteById(long location_id) throws ResourceNotFoundException;
}
