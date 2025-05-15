package com.ecommerce.e15.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ecommerce.e15.dto.BaseDTO;
import com.ecommerce.e15.dto.CategoryDTO;
import com.ecommerce.e15.dto.CategoryListDTO;
import com.ecommerce.e15.entity.Category;
import com.ecommerce.e15.exception.ResourceNotFoundException;
import com.ecommerce.e15.repository.CategoryRepository;
import com.ecommerce.e15.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	ModelMapper modelMapper;

	@Override
    public ResponseEntity<BaseDTO<CategoryListDTO>> getAllCategories() {		
		// Map the list of Category entities to a list of CategoryDTOs using ModelMapper
		List<CategoryDTO> categoryDTOs = categoryRepository.findAll().stream()
				.map(category -> modelMapper.map(category, CategoryDTO.class))
				.toList();
		
		// Wrap the list of CategoryDTOs in a CategoryListDTO
		CategoryListDTO categoryListDTO = new CategoryListDTO(categoryDTOs);
		
		// Create a BaseDTO object with status, message, and data
		BaseDTO<CategoryListDTO> response = new BaseDTO<>(HttpStatus.OK.value(), "", categoryListDTO);
		
		// Wrap the CategoryListDTO in a BaseDTO and return as ResponseEntity
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@Override
    public ResponseEntity<BaseDTO<CategoryDTO>> getCategoryById(Long id) {
		// Retrieve the category entity by ID
		Category category = categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + id));
		
		// Map the Category entity to CategoryDTO using ModelMapper
	    CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
	    
	    // Wrap the CategoryDTO in a BaseDTO
	    BaseDTO<CategoryDTO> response = new BaseDTO<>(HttpStatus.OK.value(), "", categoryDTO);
	    
	    // Return the response wrapped in a ResponseEntity
		return new ResponseEntity<>(response, HttpStatus.OK);
    }

	@Override
    public ResponseEntity<BaseDTO<CategoryDTO>> createCategory(Category category) {		
		// Save the category entity
		Category savedCategory = categoryRepository.save(category);
		
		// Map the saved entity to CategoryDTO
		CategoryDTO saveCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);
		
		// Wrap in BaseDTO and return the response
		BaseDTO<CategoryDTO> response = new BaseDTO<>(HttpStatus.CREATED.value(), "Category created successfully", saveCategoryDTO);
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

	@Override
	public ResponseEntity<BaseDTO<CategoryDTO>> updateCategory(Category updatedCategory, Long id) {
		// Find the existing category by ID using Optional
	    Optional<Category> existingCategoryOptional = categoryRepository.findById(id);

	    // Check if the category is present
	    if (existingCategoryOptional.isEmpty()) {
	        // Wrap an error message in a BaseDTO and return BAD_REQUEST
	        BaseDTO<CategoryDTO> response = new BaseDTO<>(HttpStatus.BAD_REQUEST.value(), "Category not found with ID: " + id, null);
	        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	    }
	    
	    // Get the existing category entity
	    Category existingCategory = existingCategoryOptional.get();
		
		// Update the existing category with new values
		existingCategory.setName(updatedCategory.getName());
		
		// Save the updated category
		Category savedCategory = categoryRepository.save(existingCategory);
		
		// Map the updated entity to CategoryDTO
        CategoryDTO updatedCategoryDTO = modelMapper.map(savedCategory, CategoryDTO.class);

        // Wrap in BaseDTO and return the response
        BaseDTO<CategoryDTO> response = new BaseDTO<>(HttpStatus.OK.value(), "Category updated successfully", updatedCategoryDTO);

        return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@Override
    public ResponseEntity<BaseDTO<CategoryDTO>> deleteCategory(Long id) {
		// Find the category by ID using Optional
	    Optional<Category> categoryOptional = categoryRepository.findById(id);
	    
	    // Check if the category is present
	    if (categoryOptional.isEmpty()) {
	    	BaseDTO<CategoryDTO> response = new BaseDTO<>(HttpStatus.BAD_REQUEST.value(), "Category not found with ID: " + id, null);
			return new ResponseEntity<BaseDTO<CategoryDTO>>(response, HttpStatus.BAD_REQUEST);
	    }
	    
	    // Retrieve the category entity
	    Category category = categoryOptional.get();
	    
	    // Delete the category
	    categoryRepository.deleteById(id);
	    
	    // Map the deleted category to CategoryDTO
	    CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
	    
	    // Wrap the deleted CategoryDTO in a BaseDTO
	    BaseDTO<CategoryDTO> response = new BaseDTO<>(HttpStatus.OK.value(), "Category deleted successfully", categoryDTO);
	    
	 // Return the response wrapped in a ResponseEntity
	    return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
