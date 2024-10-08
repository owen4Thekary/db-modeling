package com.example.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CusProProduct {
	private int order_num;
	private String customer_id;
	private int product_num;
	private int quantity;
	private Date order_date;
	private String name;
	private int stock;
	private int price;
	private int amount;
	private int point;
}
