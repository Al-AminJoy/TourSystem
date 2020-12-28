package com.alamin.toursystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgencyModel {
    private long agency_id;
    private String agency_name;
    private String agency_address;
    private String agency_primary_num;
    private String agency_number1;
    private String agency_number2;
    private String agency_email;
    private List<AgencyReviewModel> reviewModels;
}
