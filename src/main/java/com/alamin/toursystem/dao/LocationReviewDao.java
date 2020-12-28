package com.alamin.toursystem.dao;

import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.model.LocationReviewModel;

import java.util.List;

public interface LocationReviewDao {
     List<LocationReview> getAll();
     LocationReview findById(long review_id)throws ResourceNotFoundException;
     List<LocationReviewModel> findByLocationId(long location_id) ;
     LocationReview create(LocationReview model) throws ResourceAlreadyExistException;
     LocationReview update(LocationReview model)throws ResourceNotFoundException;
     LocationReview deleteById(long review_id) throws ResourceNotFoundException;

}
