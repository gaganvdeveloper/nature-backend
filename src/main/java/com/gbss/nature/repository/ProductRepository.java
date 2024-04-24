package com.gbss.nature.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbss.nature.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
