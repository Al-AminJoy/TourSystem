package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class JoinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long join_req_id;
    private LocalDate join_req_time;
    private long user_id;
    private long event_id;
    private boolean join_req_accepted;

    public JoinRequest(LocalDate join_req_time, long user_id, long event_id, boolean join_req_accepted) {
        this.join_req_time = join_req_time;
        this.user_id = user_id;
        this.event_id = event_id;
        this.join_req_accepted = join_req_accepted;
    }
}
