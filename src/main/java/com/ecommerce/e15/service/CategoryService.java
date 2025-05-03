package com.ecommerce.e15.service;

import java.util.List;

import com.ecommerce.e15.entity.Category;

public interface CategoryService {
	List<Category> getAllCategories();
	Category getCategoryById(Long id);
	Category createCategory(Category category);
	Category updateCategory(Category category, Long id);
	void deleteCategory(Long id);
}
