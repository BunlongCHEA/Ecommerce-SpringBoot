package com.ecommerce.e15.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.e15.entity.Product;
import com.ecommerce.e15.repository.ProductRepository;
import com.ecommerce.e15.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	 @Autowired
	    private ProductRepository productRepository;

	    @Override
	    public List<Product> getAllProducts() {
	        return productRepository.findAll();
	    }

	    @Override
	    public Product getProductById(Long id) {
	        return productRepository.findById(id).orElse(null);
	    }

	    @Override
	    public Product createProduct(Product product) {
	        return productRepository.save(product);
	    }

	    @Override
	    public Product updateProduct(Product updatedProduct, Long id) {
	        return productRepository.findById(id).map(existingProduct -> {
	        	existingProduct.setName(updatedProduct.getName());
	        	existingProduct.setPrice(updatedProduct.getPrice());
	        existingProduct.setDescription(updatedProduct.getDescription());
	        existingProduct.setQuantity(updatedProduct.getQuantity());        	
	            return productRepository.save(existingProduct);
	        }).orElseThrow(() -> new RuntimeException("Product not found with id " + id));
	    }

	    @Override
	    public void deleteProduct(Long id) {
	        productRepository.deleteById(id);
	    }
}
