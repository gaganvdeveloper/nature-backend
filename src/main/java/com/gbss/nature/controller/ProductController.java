package com.gbss.nature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.gbss.nature.entity.Product;
import com.gbss.nature.responsestructure.ResponseStructure;
import com.gbss.nature.service.ProductService;

@RestController
@RequestMapping(value = "/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

	@Autowired
	private ProductService service;

	@PostMapping
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Product>> updateProduct(@RequestBody Product product) {
		return service.updateProduct(product);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Product>> findProductById(@PathVariable int id) {
		return service.findProductById(id);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteProductById(@PathVariable int id) {
		return service.deleteProductById(id);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct() {
		return service.findAllProduct();
	}
	
	@PostMapping("/image")
	public ResponseEntity<ResponseStructure<Product>> uploadProductImage(@RequestParam MultipartFile file, @RequestParam int pid){
		System.out.println(file);
		System.out.println(pid);
		return service.uploadProductImage(file,pid);
	}

}
