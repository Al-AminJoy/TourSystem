package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.NameDao;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.repository.NameResopistory;
import com.alamin.toursystem.entity.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NameService implements NameDao {
    @Autowired
    NameResopistory nameResopistory;


    @Override
    public List<Name> getAll() {
        List<Name> nameList=new ArrayList<>();
        nameResopistory.findAll().forEach(nameList::add);
        return nameList;
    }

    @Override
    public Name findById(long name_id) throws ResourceNotFoundException {
        Name name=nameResopistory.findById(name_id).orElseThrow(ResourceNotFoundException::new);
        return name;
    }

    @Override
    public Name create(Name name) throws ResourceAlreadyExistException {
        if (nameResopistory.existsById(name.getName_id())){
            throw new ResourceAlreadyExistException();
        }
        else {
            Name savedName=nameResopistory.save(name);
            return savedName;
        }
    }

    @Override
    public Name update(Name name) throws ResourceNotFoundException {
        if (nameResopistory.existsById(name.getName_id())){
            Name updatedName=nameResopistory.save(name);
            return updatedName;
        }
        else {
            throw  new ResourceNotFoundException();
        }
    }

    @Override
    public Name deleteById(long name_id) throws ResourceNotFoundException {

        if (nameResopistory.existsById(name_id)){
            Name deletedName=nameResopistory.findById(name_id).get();
            nameResopistory.deleteById(name_id);
            return deletedName;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }
}
