package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.AgencyReview;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyReviewRepository extends CrudRepository<AgencyReview,Long> {
}
