package com.ecommerce.e15.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.e15.dto.BaseDTO;
import com.ecommerce.e15.dto.CategoryDTO;
import com.ecommerce.e15.dto.CategoryListDTO;
import com.ecommerce.e15.entity.Category;
import com.ecommerce.e15.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	@Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<BaseDTO<CategoryListDTO>> getAllCategories() {
    	// Delegate the call to the service layer
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseDTO<CategoryDTO>> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    @PostMapping
    public ResponseEntity<BaseDTO<CategoryDTO>> createCategory(@RequestBody Category category) {
        return categoryService.createCategory(category);
    }
    
    @PutMapping("/{id}")
	public ResponseEntity<BaseDTO<CategoryDTO>> updateMethodName(@PathVariable Long id, @RequestBody Category item) {
		
		return categoryService.updateCategory(item, id);
	}

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseDTO<CategoryDTO>> deleteCategory(@PathVariable Long id) {
        return categoryService.deleteCategory(id);
    }
}
