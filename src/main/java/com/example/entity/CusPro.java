package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CusPro {
    private int order_num;
    private int quantity;
    private Date order_date;
    private Date update_date;
    private String customer_id;
    private int product_num;
}
