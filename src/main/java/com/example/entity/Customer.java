package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    private String customer_id;
    private String password;
    private String name;
    private String email;
    private String grade = "normal";
    private int reserves = 0;
    private String region_code;
}
