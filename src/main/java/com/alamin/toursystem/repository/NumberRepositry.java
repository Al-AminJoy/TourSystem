package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.Number;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NumberRepositry extends CrudRepository<Number, Long> {
}
