package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.AgencyReview;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyReviewModel;
import com.alamin.toursystem.model.LocationReviewModel;

import java.util.List;

public interface AgencyReviewDao {
    public List<AgencyReview> getAll();
    public AgencyReview findById(long review_id)throws ResourceNotFoundException;
    public AgencyReview create(AgencyReviewModel model) throws ResourceAlreadyExistException;
    public AgencyReview update(AgencyReviewModel model)throws ResourceNotFoundException;
    public AgencyReview deleteById(long review_id) throws ResourceNotFoundException;
}
