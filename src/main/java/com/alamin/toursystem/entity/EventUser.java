package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class EventUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long event_user_id;
    @Column(nullable = false)
    private long event_id;
    @Column(nullable = false)
    private long user_id;

}
