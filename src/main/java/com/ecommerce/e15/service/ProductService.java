package com.ecommerce.e15.service;

import java.util.List;

import com.ecommerce.e15.entity.Product;

public interface ProductService {
	List<Product> getAllProducts();
	Product getProductById(Long id);
	Product createProduct(Product product);
	Product updateProduct(Product product, Long id);
	void deleteProduct(Long id);
}
