package com.alamin.toursystem.entity;

import com.alamin.toursystem.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Rev {
    @Id
    private long rev_id;
    @OneToOne
    private User user;
}
