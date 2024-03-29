package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.LocationDao;
import com.alamin.toursystem.dao.LocationReviewDao;
import com.alamin.toursystem.entity.Location;
import com.alamin.toursystem.model.LocationReviewModel;
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
    @Autowired
    private LocationReviewDao reviewDao;

    /**
     * Gets a List of Location
     */
    @Override
    public List<Location> getAll() {
        List<Location> locationList = new ArrayList<>();
        repository.findAll().forEach(locationList::add);
        return locationList;
    }

    /**
     * Checks Location exist or not by location _id
     */
    @Override
    public boolean findByExist(long location_id) {
        if (repository.existsById(location_id)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets an object of Location if exist by location_id
     */
    @Override
    public Location findById(long location_id) throws ResourceNotFoundException {
        Location review = repository.findById(location_id).orElseThrow(ResourceNotFoundException::new);
        return review;
    }

    /**
     * Gets a List of LocationModel
     */
    @Override
    public List<LocationModel> getAllLocation() {
        /**
         *Gets a List of Location
         */
        List<Location> locations = getAll();
        List<LocationModel> locationList = new ArrayList<>();

        for (Location location : locations) {
            /**
             *Gets a List of LocationReviewModel from ReviewDao by passing location_id
             */
            List<LocationReviewModel> reviewList = reviewDao.findByLocationId(location.getLocation_id());
            locationList.add(new LocationModel(
                    location.getLocation_id(),
                    location.getLocation_name(),
                    reviewList
            ));
        }
        return locationList;
    }

    /**
     * Gets an object of LocationModel by passing location_id
     */
    @Override
    public LocationModel findByLocationId(long location_id) throws ResourceNotFoundException {

        Location location = repository.findById(location_id).orElseThrow(ResourceNotFoundException::new);
        List<LocationReviewModel> reviewList = reviewDao.findByLocationId(location.getLocation_id());
        LocationModel locationModel = new LocationModel(
                location.getLocation_id(),
                location.getLocation_name(),
                reviewList
        );
        return locationModel;
    }

    /**
     * Creates Location
     */
    @Override
    public Location create(Location model) throws ResourceAlreadyExistException {

        if (repository.existsById(model.getLocation_id())) {
            throw new ResourceAlreadyExistException();
        } else {
            Location savedName = repository.save(model);
            return savedName;
        }
    }

    /**
     * Updates Location
     */
    @Override
    public Location update(Location model) throws ResourceNotFoundException {

        if (repository.existsById(model.getLocation_id())) {
            Location updatedLocation = repository.save(model);
            return updatedLocation;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Deletes Location exist by location_id
     */
    @Override
    public Location deleteById(long location_id) throws ResourceNotFoundException {
        if (repository.existsById(location_id)) {
            Location deletedLocation = repository.findById(location_id).get();
            repository.deleteById(location_id);
            return deletedLocation;
        } else {
            throw new ResourceNotFoundException();
        }
    }
}
