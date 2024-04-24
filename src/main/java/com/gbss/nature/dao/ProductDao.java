package com.gbss.nature.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gbss.nature.entity.Product;
import com.gbss.nature.repository.ProductRepository;

@Repository
public class ProductDao {
	@Autowired
	ProductRepository repository;
	
	public Product saveProduct(Product product) {
		return repository.save(product);
	}
	
	public Product updateProduct(Product product) {
		return repository.save(product);
	}
	
	public Optional<Product> findProductById(int id) {
		return repository.findById(id);
	}
	
	public void deleteProductById(int id) {
		repository.deleteById(id);
	}
	
	public List<Product> findAllProduct() {
		return repository.findAll();
	}
	
}
