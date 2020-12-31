package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.entity.User;
import com.alamin.toursystem.repository.UserRepository;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDao {
    @Autowired
    private UserRepository repository;

    /**
     * Gets a List of User
     */
    @Override
    public List<User> getAll() {
        List<User> userList = new ArrayList<>();
        repository.findAll().forEach(userList::add);
        return userList;
    }

    /**
     * Checks User exist or not
     */
    @Override
    public boolean findByExist(long user_id) {
        if (repository.existsById(user_id)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gets an object of User by passing user_id
     */
    @Override
    public User findById(long user_id) throws ResourceNotFoundException {
        User user = repository.findById(user_id).orElseThrow(ResourceNotFoundException::new);
        return user;
    }

    /**
     * Gets an object of User by passing email
     */
    @Override
    public User findByEmail(String email) {
        User user = repository.findByUser_email(email);
        return user;
    }

    /**
     * Creates User
     */
    @Override
    public User create(User model) throws ResourceAlreadyExistException {

        if (repository.existsById(model.getUser_id())) {
            throw new ResourceAlreadyExistException();
        } else {
            User savedUser = repository.save(model);
            return savedUser;
        }
    }

    /**
     * Updates User
     */
    @Override
    public User update(User model) throws ResourceNotFoundException {
        User user = new User(
                model.getUser_id(),
                model.getFirst_name(),
                model.getLast_name(),
                model.getUser_email(),
                model.getUser_address(),
                model.getUser_gender(),
                model.getUser_dob(),
                model.getPrimary_num(),
                model.getNum1(),
                model.getNum2());
        if (repository.existsById(model.getUser_id())) {
            User updatedUser = repository.save(user);
            return updatedUser;
        } else {
            throw new ResourceNotFoundException();
        }
    }

    /**
     * Deletes User object by passing user_id if exists
     */
    @Override
    public User deleteById(long user_id) throws ResourceNotFoundException {
        if (repository.existsById(user_id)) {
            User deletedUser = repository.findById(user_id).get();
            repository.deleteById(user_id);
            return deletedUser;
        } else {
            throw new ResourceNotFoundException();
        }
    }


}
