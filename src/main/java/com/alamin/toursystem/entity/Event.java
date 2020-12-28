package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long event_id;
    private long package_cost;
    private long people;
    private String bordering_point;
    private LocalDate event_date;
    private String event_description;
    private long location_id;
    private long agency_id;
}
