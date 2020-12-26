package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.AgencyReviewDao;
import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.entity.*;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.model.AgencyModel;
import com.alamin.toursystem.model.AgencyReviewModel;
import com.alamin.toursystem.model.LocationModel;
import com.alamin.toursystem.model.LocationReviewModel;
import com.alamin.toursystem.repository.AgencyReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class AgencyReviewService implements AgencyReviewDao {

    @Autowired private AgencyReviewRepository repository;
    @Autowired
    private UserDao userDao;
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
    public AgencyReview create(AgencyReview model) throws ResourceAlreadyExistException {
        if (repository.existsById(model.getAgency_review_id())){
            throw new ResourceAlreadyExistException();
        }
        else {
            AgencyReview savedReview=repository.save(model);
            return savedReview;
        }
    }

    @Override
    public AgencyReview update(AgencyReview model) throws ResourceNotFoundException {
        AgencyReview review=new AgencyReview(
                model.getAgency_review_id(),
                model.getAgency_rating(),
                model.getAgency_review_comment(),
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

    @Override
    public List<AgencyReviewModel> findByAgencyId(long agency_id) {
        List<AgencyReviewModel> reviewModels=new ArrayList<>();
        List<AgencyReview> reviews=new ArrayList<>();
        repository.findByAgency(agency_id).forEach(reviews::add);
        for (AgencyReview review:
                reviews) {
            try {
                User user=userDao.findById(review.getUser_id());
                reviewModels.add(new AgencyReviewModel(
                        review.getAgency_review_id(),
                        review.getAgency_rating(),
                        review.getAgency_review_comment(),
                        user.getUser_id(),
                        user.getFirst_name(),
                        user.getLast_name(),
                        user.getUser_email(),
                        user.getUser_address(),
                        user.getUser_gender(),
                        user.getUser_dob(),
                        user.getPrimary_num(),
                        user.getNum1(),
                        user.getNum2()
                ));
            } catch (ResourceNotFoundException e) {
                e.printStackTrace();
            }
        }

        return reviewModels;
    }


}
