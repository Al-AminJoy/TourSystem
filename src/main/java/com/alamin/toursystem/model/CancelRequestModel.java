package com.alamin.toursystem.model;

import com.alamin.toursystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CancelRequestModel {
    private long cancel_req_id;
    private LocalDate cancel_req_time;
    private long event_id;
    private User user;
}
