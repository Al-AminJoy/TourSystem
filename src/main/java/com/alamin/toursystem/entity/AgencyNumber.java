package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgencyNumber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long agency_number_id;
    private String agency_primary_num;
    private String agency_number1;
    private String agency_number2;

    public AgencyNumber(String agency_primary_num, String agency_number1, String agency_number2) {
        this.agency_primary_num = agency_primary_num;
        this.agency_number1 = agency_number1;
        this.agency_number2 = agency_number2;
    }
}
