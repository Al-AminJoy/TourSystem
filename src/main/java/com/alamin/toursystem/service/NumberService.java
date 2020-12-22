package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.NumberDao;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.repository.NumberRepositry;
import com.alamin.toursystem.entity.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NumberService implements NumberDao {
    @Autowired
    private NumberRepositry numberRepositry;


    @Override
    public List<Number> getAll() {
        List<Number> numberList=new ArrayList<>();
        numberRepositry.findAll().forEach(numberList::add);
        return numberList;
    }

    @Override
    public Number findById(long number_id) throws ResourceNotFoundException {
        Number number=numberRepositry.findById(number_id).orElseThrow(ResourceNotFoundException::new);
        return number;
    }

    @Override
    public Number create(Number number) throws ResourceAlreadyExistException {
        if (number==null){
            throw new ResourceAlreadyExistException();
        }
        else {
            Number savedNumber=numberRepositry.save(number);
            return savedNumber;
        }
    }

    @Override
    public Number update(Number number) throws ResourceNotFoundException {
        if (numberRepositry.existsById(number.getNumber_id())){
            Number updatedNumber=numberRepositry.save(number);
            return updatedNumber;
        }
        else {
            throw  new ResourceNotFoundException();
        }
    }

    @Override
    public Number deleteById(long number_id) throws ResourceNotFoundException {
        if (numberRepositry.existsById(number_id)){
            Number deletedNumber=numberRepositry.findById(number_id).get();
            numberRepositry.deleteById(number_id);
            return deletedNumber;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }
}
