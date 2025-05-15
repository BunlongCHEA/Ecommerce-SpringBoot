package com.ecommerce.e15.service;

import org.springframework.http.ResponseEntity;

import com.ecommerce.e15.dto.BaseDTO;
import com.ecommerce.e15.dto.CategoryDTO;
import com.ecommerce.e15.dto.CategoryListDTO;
import com.ecommerce.e15.entity.Category;

public interface CategoryService {
	public ResponseEntity<BaseDTO<CategoryListDTO>> getAllCategories();
	ResponseEntity<BaseDTO<CategoryDTO>> getCategoryById(Long id);
	ResponseEntity<BaseDTO<CategoryDTO>> createCategory(Category category);
	ResponseEntity<BaseDTO<CategoryDTO>> updateCategory(Category category, Long id);
	ResponseEntity<BaseDTO<CategoryDTO>> deleteCategory(Long id);
}
