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
public class JoinRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long join_req_id;
    @Column(nullable = false, length = 32)
    private LocalDate join_req_time;
    @Column(nullable = false)
    private long user_id;
    @Column(nullable = false)
    private long event_id;
    @Column(nullable = false)
    private boolean join_req_accepted;

}
