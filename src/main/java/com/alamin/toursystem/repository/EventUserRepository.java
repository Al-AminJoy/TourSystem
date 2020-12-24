package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.EventUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventUserRepository extends CrudRepository<EventUser,Long> {
}
