package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.AgencyReview;
import com.alamin.toursystem.entity.LocationReview;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgencyReviewRepository extends CrudRepository<AgencyReview,Long> {
    @Query("from AgencyReview where agency_id=?1")
    List<AgencyReview> findByAgency(long agency_id);
}
