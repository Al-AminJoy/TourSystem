package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//TODO: ADD review comment
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class LocationReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long location_review_id;
    private int location_rating;
    private long location_id;
    private long user_id;

    public LocationReview(int location_rating, long location_id, long user_id) {
        this.location_rating = location_rating;
        this.location_id = location_id;
        this.user_id = user_id;
    }
}
