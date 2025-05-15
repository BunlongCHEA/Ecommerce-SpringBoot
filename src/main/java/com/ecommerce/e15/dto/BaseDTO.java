package com.ecommerce.e15.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseDTO<T> {
	int statusCode;
	String message;
	T data;
}
