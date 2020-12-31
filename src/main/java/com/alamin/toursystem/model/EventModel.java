package com.alamin.toursystem.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventModel {
    private long event_id;
    private long package_cost;
    private long people;
    private String bordering_point;
    private LocalDate event_date;
    private String event_description;
    private LocationModel location;
    private AgencyModel agency;
}
