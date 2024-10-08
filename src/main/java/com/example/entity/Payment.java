package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	private String customer_id;
	private int total_price;
	private int discount = 0;
	private int actual_price = 0;

}
