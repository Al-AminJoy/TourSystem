package com.alamin.toursystem.dao;

import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.User;
import com.alamin.toursystem.model.UserModel;

import java.util.List;

public interface UserDao {
    public List<User> getAll();
    public User findById(long user_id)throws ResourceNotFoundException;
    public User create(UserModel model) throws ResourceAlreadyExistException;
    public User update(UserModel model)throws ResourceNotFoundException;
    public User deleteById(long user_id) throws ResourceNotFoundException;

}
