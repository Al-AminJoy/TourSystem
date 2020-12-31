package com.alamin.toursystem.repository;


import com.alamin.toursystem.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("from User where user_email=?1")
    User findByUser_email(String user_email);
}
