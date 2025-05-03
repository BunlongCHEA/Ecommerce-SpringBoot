package com.ecommerce.e15.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.e15.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
