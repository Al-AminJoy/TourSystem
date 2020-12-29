package com.alamin.toursystem.dao;

import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.User;
import com.alamin.toursystem.model.UserModel;

import java.util.List;

public interface UserDao {
     List<User> getAll();
     boolean findByExist(long user_id);
     User findById(long user_id)throws ResourceNotFoundException;
     UserModel findUserById(long user_id) throws ResourceNotFoundException;
     List<UserModel> getUsers();
     User create(User model) throws ResourceAlreadyExistException;
     User update(User model)throws ResourceNotFoundException;
     User deleteById(long user_id) throws ResourceNotFoundException;


}
