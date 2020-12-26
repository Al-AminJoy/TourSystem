package com.alamin.toursystem.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long user_id;
    private String first_name;
    private String last_name;
    private String user_email;
    private String user_address;
    private String user_gender;
    private LocalDate user_dob;
    private String primary_num;
    private String num1;
    private String num2;

}
