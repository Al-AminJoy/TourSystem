package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.AgencyDao;
import com.alamin.toursystem.dao.AgencyReviewDao;
import com.alamin.toursystem.entity.*;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyModel;
import com.alamin.toursystem.model.AgencyReviewModel;
import com.alamin.toursystem.repository.AgencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AgencyService implements AgencyDao {
    @Autowired
    private AgencyRepository repository;
    @Autowired
    private AgencyReviewDao reviewDao;
    @Override
    public List<Agency> getAll() {
        List<Agency> list=new ArrayList<>();
        repository.findAll().forEach(list::add);
        return list;
    }

    @Override
    public Agency findById(long agency_id) throws ResourceNotFoundException {
        Agency result=repository.findById(agency_id).orElseThrow(ResourceNotFoundException::new);
        return result;
    }
    @Override
    public List<AgencyModel> getAllAgency() {
        List<Agency> agencies=getAll();
        List<AgencyModel> models=new ArrayList<>();

        for (Agency agency:agencies) {

            List<AgencyReviewModel> reviewList=reviewDao.findByAgencyId(agency.getAgency_id());
            models.add(new AgencyModel(
                    agency.getAgency_id(),
                    agency.getAgency_name(),
                    agency.getAgency_address(),
                    agency.getAgency_primary_num(),
                    agency.getAgency_number1(),
                    agency.getAgency_number2(),
                    agency.getAgency_email(),
                    reviewList
            ));
        }
        return models;
    }
    @Override
    public AgencyModel findByAgencyId(long agency_id) throws ResourceNotFoundException {
        Agency agency=repository.findById(agency_id).orElseThrow(ResourceNotFoundException::new);
        List<AgencyReviewModel> reviewList=reviewDao.findByAgencyId(agency.getAgency_id());
        AgencyModel locationModel=new AgencyModel(
                agency.getAgency_id(),
                agency.getAgency_name(),
                agency.getAgency_address(),
                agency.getAgency_primary_num(),
                agency.getAgency_number1(),
                agency.getAgency_number2(),
                agency.getAgency_email(),
                reviewList
        );
        return locationModel;
    }
    @Override
    public Agency create(Agency model) throws ResourceAlreadyExistException {

        if (repository.existsById(model.getAgency_id())){
            throw new ResourceAlreadyExistException();
        }
        else {
            Agency created=repository.save(model);
            return created;
        }
    }

    @Override
    public Agency update(Agency model) throws ResourceNotFoundException {
        Agency agency=new Agency(
                model.getAgency_id(),
                model.getAgency_name(),
                model.getAgency_address(),
                model.getAgency_email(),
                model.getAgency_primary_num(),
                model.getAgency_number1(),
                model.getAgency_number2());
        if (repository.existsById(model.getAgency_id())){
            Agency updated=repository.save(agency);
            return updated;
        }
        else {
            throw  new ResourceNotFoundException();
        }
    }

    @Override
    public Agency deleteById(long agency_id) throws ResourceNotFoundException {
        if (repository.existsById(agency_id)){
            Agency deleted=repository.findById(agency_id).get();
            repository.deleteById(agency_id);
            return deleted;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }
}
