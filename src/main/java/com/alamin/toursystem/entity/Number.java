package com.alamin.toursystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long number_id;
    private String primary_num;
    private String num1;
    private String num2;

    public Number(String primary_num, String num1, String num2) {
        this.primary_num = primary_num;
        this.num1 = num1;
        this.num2 = num2;
    }
}
