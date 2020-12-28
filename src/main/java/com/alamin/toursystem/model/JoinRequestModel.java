package com.alamin.toursystem.model;

import com.alamin.toursystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JoinRequestModel {
    private long join_req_id;
    private LocalDate join_req_time;
    private long event_id;
    private User user;
}
