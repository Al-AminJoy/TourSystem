package com.alamin.toursystem.entity;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Name {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long name_id;
    private String first_name;
    private String last_name;


    public Name(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }


}
