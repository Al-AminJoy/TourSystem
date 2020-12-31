package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.AgencyReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyReviewModel;
import java.util.List;

public interface AgencyReviewDao {

    List<AgencyReview> getAll();

    AgencyReview findById(long review_id) throws ResourceNotFoundException;

    List<AgencyReviewModel> findByAgencyId(long agency_id);

    AgencyReview create(AgencyReview model) throws ResourceAlreadyExistException, ResourceNotFoundException;

    AgencyReview update(AgencyReview model) throws ResourceNotFoundException;

    AgencyReview deleteById(long review_id) throws ResourceNotFoundException;

}
