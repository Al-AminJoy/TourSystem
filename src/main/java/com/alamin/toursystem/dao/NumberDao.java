package com.alamin.toursystem.dao;

import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.Number;

import java.util.List;

public interface NumberDao {
    public List<Number> getAll();
    public Number findById(long number_id)throws ResourceNotFoundException;
    public Number create(Number number) throws ResourceAlreadyExistException;
    public Number update(Number number)throws ResourceNotFoundException;
    public Number deleteById(long number_id) throws ResourceNotFoundException;
}
