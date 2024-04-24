package com.gbss.nature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.gbss.nature.dao.ProductDao;
import com.gbss.nature.entity.Image;
import com.gbss.nature.entity.Product;
import com.gbss.nature.responsestructure.ResponseStructure;

@Service
public class ProductService {

	@Autowired
	ProductDao productDao;

	@Autowired
	CloudinaryService cloudinaryService;

	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Product Saved Successfully...");
		structure.setBody(productDao.saveProduct(product));
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Product Updated Successfully...");
		structure.setBody(productDao.updateProduct(product));
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> findProductById(int id) {
		Optional<Product> optinal = productDao.findProductById(id);
		if (optinal.isEmpty())
			throw new RuntimeException("Invalid Product ID");
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Product Saved Successfully...");
		structure.setBody(optinal.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteProductById(int id) {
		Optional<Product> optinal = productDao.findProductById(id);
		if (optinal.isEmpty())
			throw new RuntimeException("Invalid Product ID");
		productDao.deleteProductById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Product Deleted Successfully...");
		structure.setBody("Product Deleted successfully...");
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAllProduct() {
		ResponseStructure<List<Product>> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("All Products Fetched SUccessfully...");
		structure.setBody(productDao.findAllProduct());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Product>> uploadProductImage(MultipartFile file, int pid) {
		Optional<Product> optional = productDao.findProductById(pid);
		if (optional.isEmpty())
			throw new RuntimeException("Invalid Product Id : " + pid);
		
		Product product = optional.get();
		
		String url = cloudinaryService.uploadFile(file, "product_images");
		System.out.println(url);
		if (url == null)
			System.out.println("Unable To Upload Image to Clouinary");
//			throw new RuntimeException("Unable To Upload Product Image");
		List<Image> images = product.getImages();
		if (images == null)
			images = new ArrayList<>();
		images.add(Image.builder().folderName("product_images").imagePath(url).build());
		product.setImages(images);
		productDao.updateProduct(product);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Image Uploaded Successfully... for Product ID : " + product.getId());
		structure.setBody(product);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

}
