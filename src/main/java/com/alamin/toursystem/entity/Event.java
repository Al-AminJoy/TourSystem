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
private String bodering_point;
private LocalDate event_date;
private String event_description;
private long location_id;
private long agency_id;

    public Event(long package_cost, long people, String bodering_point, LocalDate event_date, String event_description, long location_id, long agency_id) {
        this.package_cost = package_cost;
        this.people = people;
        this.bodering_point = bodering_point;
        this.event_date = event_date;
        this.event_description = event_description;
        this.location_id = location_id;
        this.agency_id = agency_id;
    }
}
