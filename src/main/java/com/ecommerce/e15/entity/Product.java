package com.ecommerce.e15.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String description;
//	private Double discount;
//	private String image;
	private Double price;
	private String name;
	private Integer quantity;
//	private Double specialPrice;
}
