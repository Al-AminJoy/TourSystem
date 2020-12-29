package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AgencyReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long agency_review_id;
    @Column(nullable = false ,length = 1)
    private int agency_rating;
    @Column(length = 255)
    private String agency_review_comment;
    @Column(nullable = false)
    private long agency_id;
    @Column(nullable = false)
    private long user_id;
}
