package com.alamin.toursystem.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
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
