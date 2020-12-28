package com.alamin.toursystem.dao;

import com.alamin.toursystem.entity.Agency;
import com.alamin.toursystem.entity.AgencyReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyModel;
import com.alamin.toursystem.model.AgencyReviewModel;

import java.util.List;

public interface AgencyDao {
     List<Agency> getAll();
     Agency findById(long agency_id)throws ResourceNotFoundException;
     AgencyModel findByAgencyId(long agency_id) throws ResourceNotFoundException;
     List<AgencyModel> getAllAgency();
     Agency create(Agency model) throws ResourceAlreadyExistException;
     Agency update(Agency model)throws ResourceNotFoundException;
     Agency deleteById(long agency_id) throws ResourceNotFoundException;

}
