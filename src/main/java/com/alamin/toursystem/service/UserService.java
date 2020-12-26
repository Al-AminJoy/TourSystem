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
//TODO: Perform All Complex Queries
//TODO: Batch Processing
//TODO:
//TODO: Write Blank Methods Code
//TODO: Disable Unused Methods Code in Controller
//TODo: Create Generic Method
//TODO: Add Documentation
//TODO: Format All Codes

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
    public User create(User model) throws ResourceAlreadyExistException {
       /* Name name=new Name(
                model.getFirst_name(),
                model.getLast_name());

        Number number=new Number(
                model.getPrimary_num(),
                model.getNum1(),
                model.getNum2());

        UserModel user=new UserModel(name,
                model.getUser_email(),
                model.getUser_address(),
                model.getUser_gender(),
                model.getUser_dob(),
                number);

        */
        if (repository.existsById(model.getUser_id())){
            throw  new ResourceAlreadyExistException();
        }
        else {
            User savedUser=repository.save(model);
            return savedUser;
        }
    }

    @Override
    public User update(User model) throws ResourceNotFoundException {
        /*Number updatedNumbers = new Number( model.getUser_id(),
                model.getPrimary_num(),
                model.getNum1(),
                model.getNum2());

        Name updatedName =new Name(model.getUser_id(),
                model.getFirst_name(),
                model.getLast_name());

        UserModel user = new UserModel(model.getUser_id(),
                updatedName,
                model.getUser_email(),
                model.getUser_address(),
                model.getUser_gender(),
                model.getUser_dob(),
                updatedNumbers);

         */
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
