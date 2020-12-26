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
public class CancelRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long cancel_req_id;
    private LocalDate cancel_req_time;
    private long user_id;
    private long event_id;
    private boolean cancel_req_accepted;

    public CancelRequest(LocalDate cancel_req_time, long user_id, long event_id, boolean cancel_req_accepted) {
        this.cancel_req_time = cancel_req_time;
        this.user_id = user_id;
        this.event_id = event_id;
        this.cancel_req_accepted = cancel_req_accepted;
    }
}
