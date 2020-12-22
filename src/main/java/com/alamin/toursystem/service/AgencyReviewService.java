package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.AgencyReviewDao;
import com.alamin.toursystem.entity.AgencyReview;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyReviewModel;
import com.alamin.toursystem.repository.AgencyReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AgencyReviewService implements AgencyReviewDao {

    @Autowired private AgencyReviewRepository repository;
    @Override
    public List<AgencyReview> getAll() {
        List<AgencyReview> reviewList=new ArrayList<>();
        repository.findAll().forEach(reviewList::add);
        return reviewList;
    }

    @Override
    public AgencyReview findById(long review_id) throws ResourceNotFoundException {
        AgencyReview review=repository.findById(review_id).orElseThrow(ResourceNotFoundException::new);
        return review;
    }

    @Override
    public AgencyReview create(AgencyReviewModel model) throws ResourceAlreadyExistException {
        AgencyReview review=new AgencyReview(
                model.getAgency_rating(),
                model.getAgency_id(),
                model.getUser_id());
        if (repository.existsById(model.getAgency_review_id())){
            throw new ResourceAlreadyExistException();
        }
        else {
            AgencyReview savedReview=repository.save(review);
            return savedReview;
        }
    }

    @Override
    public AgencyReview update(AgencyReviewModel model) throws ResourceNotFoundException {
        AgencyReview review=new AgencyReview(
                model.getAgency_review_id(),
                model.getAgency_rating(),
                model.getAgency_id(),
                model.getUser_id());
        if (repository.existsById(model.getAgency_review_id())){
            AgencyReview updatedReview=repository.save(review);
            return updatedReview;
        }
        else {
            throw  new ResourceNotFoundException();
        }
    }

    @Override
    public AgencyReview deleteById(long review_id) throws ResourceNotFoundException {
        if (repository.existsById(review_id)){
            AgencyReview deletedReview=repository.findById(review_id).get();
            repository.deleteById(review_id);
            return deletedReview;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }
}
