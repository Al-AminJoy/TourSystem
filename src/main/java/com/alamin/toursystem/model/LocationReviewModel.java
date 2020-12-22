package com.alamin.toursystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationReviewModel {
    private long location_review_id;
    private int location_rating;
    private long location_id;
    private long user_id;
}
