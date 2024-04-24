package com.gbss.nature.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String description;
	private String longDescription;
	private double price;
	private double discountedPrice;
	private int stockQuantity;
	private String brand;
	private String category;
	private String tags;
	private LocalDateTime addedDate;
	private String manufacturer;
	private int averageRating;
	private int totalReviews;
	private String availableStatus;
	@OneToMany
	private List<Product> relatedProducts;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Image> images;
}
