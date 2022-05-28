package com.alamin.toursystem.controller;

import com.alamin.toursystem.configuration.ConfigJWT;
import com.alamin.toursystem.dao.UserDao;
import com.alamin.toursystem.exception.ResourceAlreadyExistException;
import com.alamin.toursystem.exception.ResourceNotFoundException;
import com.alamin.toursystem.entity.User;
import com.alamin.toursystem.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserDao dao;
    private final ConfigJWT jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository repository;


    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody User user) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Username or password not matched", e);
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
        final String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticatedUser(user.getUserName(), token));
    }

    private static class AuthenticatedUser {
        private final String username;
        private final String accessToken;

        public AuthenticatedUser(String username, String accessToken) {
            this.username = username;
            this.accessToken = accessToken;
        }

        public String getUsername() {
            return username;
        }

        public String getToken() {
            return accessToken;
        }
    }

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

    @GetMapping("/all-users")
    public ResponseEntity<List<User>> user() {
        List<User> allUsers = (List<User>) repository.findAll();
        return ResponseEntity.ok(allUsers);
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
