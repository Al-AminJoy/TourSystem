package com.alamin.toursystem.service;

import com.alamin.toursystem.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service(value = "userDetailsService")
public class TourUserDetailsService implements UserDetailsService {
    private final UserService usersService;

    public TourUserDetailsService(UserService usersService) {
        this.usersService = usersService;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = usersService.getUserByUserName(s);
        if (!optionalUser.isPresent()) optionalUser = usersService.getUserByEmail(s);
        optionalUser.orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new TourUserDetails(optionalUser.get());
    }
}
