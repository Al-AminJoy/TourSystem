package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.Agency;
import com.alamin.toursystem.entity.AgencyReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyModel;
import com.alamin.toursystem.model.AgencyReviewModel;

import java.util.List;

public interface AgencyDao {
    public List<Agency> getAll();
    public Agency findById(long agency_id)throws ResourceNotFoundException;
    public Agency create(Agency model) throws ResourceAlreadyExistException;
    public Agency update(Agency model)throws ResourceNotFoundException;
    public Agency deleteById(long agency_id) throws ResourceNotFoundException;
    public AgencyModel findByAgencyId(long agency_id) throws ResourceNotFoundException;
    public List<AgencyModel> getAllAgency();
}
