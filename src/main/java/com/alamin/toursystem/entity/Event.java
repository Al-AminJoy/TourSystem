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
    @Column(nullable = false, length = 9)
    private long package_cost;
    @Column(nullable = false, length = 3)
    private int people;
    @Column(nullable = false, length = 32)
    private String bordering_point;
    @Column(nullable = false, length = 32)
    private LocalDate event_date;
    private String event_description;
    @Column(nullable = false)
    private long location_id;
    @Column(nullable = false)
    private long agency_id;
}
