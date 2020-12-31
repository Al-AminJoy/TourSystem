package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.LocationReview;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationReviewRepository extends CrudRepository<LocationReview, Long> {
    @Query("from LocationReview where location_id=?1")
    List<LocationReview> findByLocation(long location_id);
}
