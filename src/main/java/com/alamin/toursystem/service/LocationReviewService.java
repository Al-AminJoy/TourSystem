package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.LocationReviewDao;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.LocationReview;
import com.alamin.toursystem.model.LocationReviewModel;
import com.alamin.toursystem.repository.LocationReviewRepository;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationReviewService implements LocationReviewDao {
    @Autowired
    private LocationReviewRepository reviewRepository;

    @Override
    public List<LocationReview> getAll() {
        List<LocationReview> reviewList=new ArrayList<>();
        reviewRepository.findAll().forEach(reviewList::add);
        return reviewList;
    }

    @Override
    public LocationReview findById(long review_id) throws ResourceNotFoundException {
        LocationReview review=reviewRepository.findById(review_id).orElseThrow(ResourceNotFoundException::new);
        return review;
    }

    @Override
    public LocationReview create(LocationReviewModel model) throws ResourceAlreadyExistException {
        LocationReview createReview = new LocationReview(
                model.getLocation_rating(),
                model.getLocation_id(),
                model.getUser_id());
        if (reviewRepository.existsById(model.getLocation_review_id())){
            throw new ResourceAlreadyExistException();
        }
        else {
            LocationReview savedReview=reviewRepository.save(createReview);
            return savedReview;
        }
    }

    @Override
    public LocationReview update(LocationReviewModel model) throws ResourceNotFoundException {
        LocationReview locationReview=new LocationReview(
                model.getLocation_review_id(),
                model.getLocation_rating(),
                model.getLocation_id(),
                model.getUser_id());
        if (reviewRepository.existsById(model.getLocation_review_id())){
            LocationReview updatedReview=reviewRepository.save(locationReview);
            return updatedReview;
        }
        else {
            throw  new ResourceNotFoundException();
        }
    }

    @Override
    public LocationReview deleteById(long review_id) throws ResourceNotFoundException {
        if (reviewRepository.existsById(review_id)){
            LocationReview deletedReview=reviewRepository.findById(review_id).get();
            reviewRepository.deleteById(review_id);
            return deletedReview;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }
}
