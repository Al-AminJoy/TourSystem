package com.alamin.toursystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgencyReviewModel {
    private long agency_review_id;
    private int agency_rating;
    private long agency_id;
    private long user_id;
}
