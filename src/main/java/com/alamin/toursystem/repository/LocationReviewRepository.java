package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.LocationReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationReviewRepository extends CrudRepository<LocationReview,Long> {
}
