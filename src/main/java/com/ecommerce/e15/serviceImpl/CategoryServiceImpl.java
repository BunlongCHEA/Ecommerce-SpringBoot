package com.ecommerce.e15.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.e15.entity.Category;
import com.ecommerce.e15.repository.CategoryRepository;
import com.ecommerce.e15.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;

	@Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

	@Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

	@Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

	@Override
	public Category updateCategory(Category updatedCategory, Long id) {
//		Optional<Category> existingItem = categoryRepository.findById(id);
//
//		if (existingItem.isPresent()) {
//			existingItem.setName(category.getName());
//			categoryRepository.save(existingItem.get());
//			return "Update success...";
//		}
//		return "Item does not exist";
		// Find the existing category by ID
	    return categoryRepository.findById(id).map(existingCategory -> {
	        // Update the existing category's name
	        existingCategory.setName((updatedCategory.getName()));
	        // Save the updated category
	        return categoryRepository.save(existingCategory);
	    }).orElseThrow(() -> new RuntimeException("Item does not exist"));
	}
	
	@Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
