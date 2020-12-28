package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.CancelRequest;
import com.alamin.toursystem.entity.JoinRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CancelRequestRepository extends CrudRepository<CancelRequest,Long> {
    @Query("from CancelRequest where event_id=?1")
    List<CancelRequest> findByEvent(long event_id);
}
