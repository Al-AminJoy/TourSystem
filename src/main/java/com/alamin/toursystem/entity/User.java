package com.alamin.toursystem.entity;

import com.alamin.toursystem.entity.Name;
import com.alamin.toursystem.entity.Number;
import lombok.*;

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
    @OneToOne(cascade=CascadeType.ALL)
    private Name name;
    private String user_email;
    private String user_address;
    private String user_gender;
    private LocalDate user_dob;
    @OneToOne(cascade=CascadeType.ALL)
    private Number user_number;

    public User(Name name, String user_email, String user_address, String user_gender, LocalDate user_dob, Number user_number) {
        this.name = name;
        this.user_email = user_email;
        this.user_address = user_address;
        this.user_gender = user_gender;
        this.user_dob = user_dob;
        this.user_number = user_number;
    }


}
