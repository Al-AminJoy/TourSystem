package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.EventUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventUserRepository extends CrudRepository<EventUser, Long> {
    @Query("from EventUser where event_id=?1")
    List<EventUser> findByEvent(long event_id);
}
