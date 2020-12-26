package com.alamin.toursystem.repository;

import com.alamin.toursystem.entity.Agency;
import com.alamin.toursystem.model.AgencyModel;
import org.springframework.data.repository.CrudRepository;

public interface AgencyRepository extends CrudRepository<Agency,Long> {
}
