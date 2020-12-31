package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.LocationDao;
import com.alamin.toursystem.dao.LocationReviewDao;
import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.entity.User;
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
    @Autowired
    private UserDao userDao;
    @Autowired
    private LocationDao locationDao;

    /**
     * Gets a List of LocationReview
     */
    @Override
    public List<LocationReview> getAll() {
        List<LocationReview> reviewList = new ArrayList<>();
        reviewRepository.findAll().forEach(reviewList::add);
        return reviewList;
    }

    /**
     * Gets an Object of LocationReview if exist by review_id
     */
    @Override
    public LocationReview findById(long review_id) throws ResourceNotFoundException {
        LocationReview review = reviewRepository.findById(review_id).orElseThrow(ResourceNotFoundException::new);
        return review;
    }

    /**
     * Gets a List of LocationReviewModel
     */
    @Override
    public List<LocationReviewModel> findByLocationId(long location_id) {
        List<LocationReviewModel> reviewModels = new ArrayList<>();
        /**
         *Gets a List of LocationReview
         */
        List<LocationReview> reviews = getAll();
        for (LocationReview review :
                reviews) {
            try {
                /**
                 *Gets an object of User from UserDao by passing user_id
                 */
                User user = userDao.findById(review.getUser_id());
                reviewModels.add(new LocationReviewModel(
                        review.getLocation_review_id(),
                        review.getLocation_rating(),
                        review.getLocation_review_comment(),
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

    /**
     * Creates LocationReview
     */
    @Override
    public LocationReview create(LocationReview model) throws ResourceAlreadyExistException, ResourceNotFoundException {
        /**
         *checks LocationReview does exist by id
         * checks Location exist or not from LocationDao by passing location_id
         * checks User exist or not from UserDao by passing user_id
         */
        if (reviewRepository.existsById(model.getLocation_review_id())) {
            throw new ResourceAlreadyExistException();
        } else if (locationDao.findByExist(model.getLocation_id()) == false) {
            throw new ResourceNotFoundException();
        } else if (userDao.findByExist(model.getUser_id()) == false) {
            throw new ResourceNotFoundException();
        } else {
            LocationReview savedReview = reviewRepository.save(model);
            return savedReview;
        }
    }

    /**
     * Updates LocationReview
     */
    @Override
    public LocationReview update(LocationReview model) throws ResourceNotFoundException {
        LocationReview locationReview = new LocationReview(
                model.getLocation_review_id(),
                model.getLocation_rating(),
                model.getLocation_review_comment(),
                model.getLocation_id(),
                model.getUser_id());
        /**
         *checks LocationReview does exist by id
         * checks Location exist or not from LocationDao by passing location_id
         * checks User exist or not from UserDao by passing user_id
         */
        if (reviewRepository.existsById(model.getLocation_review_id())) {
            if (locationDao.findByExist(model.getLocation_id()) == false) {
                throw new ResourceNotFoundException();
            } else if (userDao.findByExist(model.getUser_id()) == false) {
                throw new ResourceNotFoundException();
            } else {
                LocationReview updatedReview = reviewRepository.save(locationReview);
                return updatedReview;
            }

        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Deletes LocationReview if its exist by review_id
     */
    @Override
    public LocationReview deleteById(long review_id) throws ResourceNotFoundException {
        if (reviewRepository.existsById(review_id)) {
            LocationReview deletedReview = reviewRepository.findById(review_id).get();
            reviewRepository.deleteById(review_id);
            return deletedReview;
        } else {
            throw new ResourceNotFoundException();
        }
    }

}
