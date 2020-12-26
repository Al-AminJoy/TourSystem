package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.CancelRequest;
import com.alamin.toursystem.entity.JoinRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CancelRequestRepository extends CrudRepository<CancelRequest,Long> {

}
