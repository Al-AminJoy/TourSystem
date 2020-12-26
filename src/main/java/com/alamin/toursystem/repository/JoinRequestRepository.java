package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.JoinRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface JoinRequestRepository extends CrudRepository<JoinRequest,Long> {
    @Query("from JoinRequest where event_id=?1")
    List<JoinRequest> findByEvent(long event_id);

}
