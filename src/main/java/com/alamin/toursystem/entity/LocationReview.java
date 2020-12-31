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
    @Column(nullable = false, length = 1)
    private int location_rating;
    @Column(length = 255)
    private String location_review_comment;
    @Column(nullable = false)
    private long location_id;
    @Column(nullable = false)
    private long user_id;
}
