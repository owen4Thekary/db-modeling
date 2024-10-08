package com.example.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Product {
    private int product_num;
    private int price;
    private int stock;
    private String manufaturer;
    private String brand;
    private String image;
    private String name;
    private float rate;
    private Date create_date;
    private Date update_date;
}
