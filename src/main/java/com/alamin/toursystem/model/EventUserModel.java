package com.alamin.toursystem.model;

import com.alamin.toursystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventUserModel {
    private long event_user_id;
    private long event_id;
    private User user;
}
