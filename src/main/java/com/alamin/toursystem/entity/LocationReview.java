package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LocationReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_review_id;
    private int location_rating;
    private String location_review_comment;
    private long location_id;
    private long user_id;
}
