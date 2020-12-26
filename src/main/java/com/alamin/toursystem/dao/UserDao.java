package com.alamin.toursystem.dao;

import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.User;

import java.util.List;

public interface UserDao {
    public List<User> getAll();
    public User findById(long user_id)throws ResourceNotFoundException;
    public User create(User model) throws ResourceAlreadyExistException;
    public User update(User model)throws ResourceNotFoundException;
    public User deleteById(long user_id) throws ResourceNotFoundException;

}
