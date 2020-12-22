package com.alamin.toursystem.service;

import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.entity.Name;
import com.alamin.toursystem.entity.Number;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.model.UserModel;
import com.alamin.toursystem.repository.UserRepository;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDao {
    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAll() {
        List<User> userList=new ArrayList<>();
        repository.findAll().forEach(userList::add);
        return userList;
    }

    @Override
    public User findById(long user_id) throws ResourceNotFoundException {
        User user=repository.findById(user_id).orElseThrow(ResourceNotFoundException::new);
        return user;
    }

    @Override
    public User create(UserModel model) throws ResourceAlreadyExistException {
        Name name=new Name(
                model.getFirst_name(),
                model.getLast_name());

        Number number=new Number(
                model.getPrimary_num(),
                model.getNum1(),
                model.getNum2());

        User user=new User(name,
                model.getUser_email(),
                model.getUser_address(),
                model.getUser_gender(),
                model.getUser_dob(),
                number);
        if (repository.existsById(model.getUser_id())){
            throw  new ResourceAlreadyExistException();
        }
        else {
            User savedUser=repository.save(user);
            return savedUser;
        }
    }

    @Override
    public User update(UserModel model) throws ResourceNotFoundException {
        Number updatedNumbers = new Number( model.getUser_id(),
                model.getPrimary_num(),
                model.getNum1(),
                model.getNum2());

        Name updatedName =new Name(model.getUser_id(),
                model.getFirst_name(),
                model.getLast_name());

        User user = new User(model.getUser_id(),
                updatedName,
                model.getUser_email(),
                model.getUser_address(),
                model.getUser_gender(),
                model.getUser_dob(),
                updatedNumbers);
        if (repository.existsById(model.getUser_id())){
            User updatedUser=repository.save(user);
            return updatedUser;
        }
        else {
            throw  new ResourceNotFoundException();
        }
    }

    @Override
    public User deleteById(long user_id) throws ResourceNotFoundException {
        if (repository.existsById(user_id)){
            User deletedUser=repository.findById(user_id).get();
            repository.deleteById(user_id);
            return deletedUser;
        }
        else{
            throw new ResourceNotFoundException();
        }
    }
}
