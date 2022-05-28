package com.alamin.toursystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @Column(nullable = false, length = 32)
    private String first_name;
    @Column(nullable = false, length = 32)
    private String last_name;
    @Column(nullable = false, length = 32)
    private String user_email;
    @Column(nullable = false, length = 64)
    private String user_address;
    @Column(nullable = false, length = 6)
    private String user_gender;
    @Column(nullable = false, length = 32)
    private LocalDate user_dob;
    @Column(nullable = false, length = 11)
    private String primary_num;
    @Column(length = 11)
    private String num1;
    @Column(length = 11)
    private String num2;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Column(unique = true)
    private String userName;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    public User(User users) {
        this.user_id = users.getUser_id();
        this.userName = users.getUserName();
//        this.email = users.getEmail();
        this.password = users.getPassword();
//        this.isAccountNonExpired = users.getIsAccountNonExpired();
//        this.isAccountNonLocked = users.getIsAccountNonLocked();
//        this.isCredentialsNonExpired = users.getIsCredentialsNonExpired();
//        this.isEnabled = users.getIsEnabled();
//        this.isVerified = users.getIsVerified();
        this.roles = users.getRoles();
    }
}
