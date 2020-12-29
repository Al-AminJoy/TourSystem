package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Agency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long agency_id;
    @Column(nullable = false ,length = 32)
    private String agency_name;
    @Column(nullable = false ,length = 64)
    private String agency_address;
    @Column(nullable = false ,length = 32)
    private String agency_email;
    @Column(nullable = false ,length = 11)
    private String agency_primary_num;
    @Column(length = 11)
    private String agency_number1;
    @Column(length = 11)
    private String agency_number2;

}
