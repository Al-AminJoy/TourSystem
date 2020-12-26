package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.AgencyReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyReviewModel;
import java.util.List;

public interface AgencyReviewDao {
    public List<AgencyReview> getAll();
    public AgencyReview findById(long review_id)throws ResourceNotFoundException;
    public AgencyReview create(AgencyReview model) throws ResourceAlreadyExistException;
    public AgencyReview update(AgencyReview model)throws ResourceNotFoundException;
    public AgencyReview deleteById(long review_id) throws ResourceNotFoundException;
    public List<AgencyReviewModel> findByAgencyId(long agency_id);
}
