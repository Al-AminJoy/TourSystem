package com.alamin.toursystem.service;

import com.alamin.toursystem.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TourUserDetails extends User implements UserDetails {
    public TourUserDetails(User user) {
        super(user);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        super.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRole())));
//        super.getRoles().forEach(role -> role.getPermissions().forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()))));
//        super.getPermissions().forEach(permission -> grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName())));
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
//        return super.getIsAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
//        return super.getIsAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
//        return super.getIsCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
//        return super.getIsEnabled();
        return true;
    }
}
