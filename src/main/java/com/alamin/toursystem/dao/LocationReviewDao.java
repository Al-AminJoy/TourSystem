package com.alamin.toursystem.dao;

import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.model.LocationReviewModel;

import java.util.List;

public interface LocationReviewDao {
    public List<LocationReview> getAll();
    public LocationReview findById(long review_id)throws ResourceNotFoundException;
    public LocationReview create(LocationReviewModel model) throws ResourceAlreadyExistException;
    public LocationReview update(LocationReviewModel model)throws ResourceNotFoundException;
    public LocationReview deleteById(long review_id) throws ResourceNotFoundException;
}
