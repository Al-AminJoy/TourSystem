package com.alamin.toursystem.dao;

import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.Name;

import java.util.List;

public interface NameDao {
    public List<Name> getAll();
    public Name findById(long name_id)throws ResourceNotFoundException;
    public Name create(Name name) throws ResourceAlreadyExistException;
    public Name update(Name name)throws ResourceNotFoundException;
    public Name deleteById(long name_id) throws ResourceNotFoundException;

}
