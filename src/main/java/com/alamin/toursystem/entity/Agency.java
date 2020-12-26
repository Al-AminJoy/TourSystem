package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long agency_id;
    private String agency_name;
    private String agency_address;
    private String agency_email;
    private String agency_primary_num;
    private String agency_number1;
    private String agency_number2;

}
