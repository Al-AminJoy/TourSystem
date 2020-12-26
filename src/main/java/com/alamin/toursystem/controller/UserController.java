package com.alamin.toursystem.controller;

import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserDao dao;

    @GetMapping("")
    public ResponseEntity<List<User>> readUsers() {
        return ResponseEntity.ok(dao.getAll());
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<User> readUser(@PathVariable long user_id) {
        try {
            return ResponseEntity.ok(dao.findById(user_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("")
    public ResponseEntity<User> createUser(@RequestBody User model) {
        try {

            User createdUser=dao.create(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (ResourceAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("")
    public ResponseEntity<User> updateUser(@RequestBody User model) {
        try {
            User updateUser = dao.update(model);
            return ResponseEntity.status(HttpStatus.CREATED).body(updateUser);
        } catch ( ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();

        }
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<User> deleteUser(@PathVariable long user_id) {
        try {
            return ResponseEntity.ok(dao.deleteById(user_id));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
