package com.alamin.toursystem.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    @Column(nullable = false ,length = 32)
    private String first_name;
    @Column(nullable = false ,length = 32)
    private String last_name;
    @Column(nullable = false ,length = 32)
    private String user_email;
    @Column(nullable = false ,length = 64)
    private String user_address;
    @Column(nullable = false ,length = 6)
    private String user_gender;
    @Column(nullable = false ,length = 32)
    private LocalDate user_dob;
    @Column(nullable = false ,length = 11)
    private String primary_num;
    @Column(length = 11)
    private String num1;
    @Column(length = 11)
    private String num2;

}
