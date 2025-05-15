package com.ecommerce.e15.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String name;

//	public Object getName() {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public void setName(Object name2) {
//		// TODO Auto-generated method stub
//		
//	}
}
