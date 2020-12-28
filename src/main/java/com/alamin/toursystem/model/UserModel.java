package com.alamin.toursystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserModel {
    private long user_id;
    private Name name;
    private String user_email;
    private String user_address;
    private String user_gender;
    private LocalDate user_dob;
    private Number user_number;
}
