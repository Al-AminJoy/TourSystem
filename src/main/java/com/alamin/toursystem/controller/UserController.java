package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao dao;

    /**
     * Takes user_id  as input and returns an object of User
     */
    @GetMapping("/{user_id}")
    public ResponseEntity<User> readUser(@PathVariable long user_id) {
        try {
            return ResponseEntity.ok(dao.findById(user_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/data")
    public User user() {
       return new User();
    }



    /**
     * Takes User object as input and returns an object of User
     */
    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getFirst_name() == null
                    || model.getLast_name() == null
                    || model.getUser_email() == null
                    || model.getUser_address() == null
                    || model.getUser_gender() == null
                    || model.getUser_dob() == null
                    || model.getPrimary_num() == null) {
                return ResponseEntity.badRequest().build();
            } else {
                /**
                 *checking the column value sizes
                 */
                if (model.getFirst_name().length() > 32
                        || model.getLast_name().length() > 32
                        || model.getUser_email().length() > 32
                        || model.getUser_address().length() > 64
                        || model.getUser_gender().length() > 6
                        || model.getPrimary_num().length() > 11) {
                    return ResponseEntity.badRequest().build();
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.create(model));
                }
            }

        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Takes an User with an user_id as input and returns an object of User
     */
    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User model) {
        try {
            /**
             *Does not allow null value as input
             */
            if (model.getUser_id() <= 0
                    || model.getFirst_name() == null
                    || model.getLast_name() == null
                    || model.getUser_email() == null
                    || model.getUser_address() == null
                    || model.getUser_gender() == null
                    || model.getUser_dob() == null
                    || model.getPrimary_num() == null) {
                return ResponseEntity.badRequest().build();
            } else {
                /**
                 *checking the column value sizes
                 */
                if (model.getFirst_name().length() > 32
                        || model.getLast_name().length() > 32
                        || model.getUser_email().length() > 32
                        || model.getUser_address().length() > 64
                        || model.getUser_gender().length() > 6
                        || model.getPrimary_num().length() > 11) {
                    return ResponseEntity.badRequest().build();
                } else {
                    return ResponseEntity.status(HttpStatus.CREATED).body(dao.update(model));
                }
            }

        } catch (ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }

}
