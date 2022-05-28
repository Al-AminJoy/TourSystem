package com.alamin.toursystem.dao;

import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> getAll();

    boolean findByExist(long user_id);

    User findById(long user_id) throws ResourceNotFoundException;

    User findByEmail(String email) throws ResourceNotFoundException;

    User create(User model) throws ResourceAlreadyExistException;

    User update(User model) throws ResourceNotFoundException;

    User deleteById(long user_id) throws ResourceNotFoundException;

    public Optional<User> getUserByUserName(String userName);
    
    Optional<User> getUserByEmail(String email);
}
