package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EventUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long event_user_id;
    private long event_id;
    private long user_id;

    public EventUser(long event_id, long user_id) {
        this.event_id = event_id;
        this.user_id = user_id;
    }
}
