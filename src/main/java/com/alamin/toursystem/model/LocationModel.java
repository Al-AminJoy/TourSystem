package com.alamin.toursystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationModel {
    private long location_id;
    private String location_name;
    private List<LocationReviewModel> location_reviews;
}
