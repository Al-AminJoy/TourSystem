package com.alamin.toursystem.model;

import com.alamin.toursystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EventUserModel {
    private long event_id;
    private User user;
}
