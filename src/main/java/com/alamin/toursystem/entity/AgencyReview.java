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
@Entity
public class AgencyReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long agency_review_id;
    private int agency_rating;
    private String agency_review_comment;
    private long agency_id;
    private long user_id;
}
