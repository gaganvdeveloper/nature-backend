package com.gbss.nature.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gbss.nature.entity.Orders;
import com.gbss.nature.repository.OrdresRepository;

@Repository
public class OrdersDao {
	
	@Autowired
	OrdresRepository ordresRepository;
	
	public Orders createOrders(Orders orders) {
		return ordresRepository.save(orders);
	}
	
	public Orders updateOrders(Orders orders) {
		return ordresRepository.save(orders);
	}
	
	
	

	public Optional<Orders> findOrdersById(String id) {
		return ordresRepository.findById(id);
	}

	public List<Orders> findAllOrders() {
		return ordresRepository.findAll();
	}
	
	
}
