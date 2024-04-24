package com.gbss.nature.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gbss.nature.entity.Address;
import com.gbss.nature.repository.AddressRepository;

@Repository
public class AddressDao {
	@Autowired
	AddressRepository addressRepository;
	
	public Address saveAddress(Address address) {
		return addressRepository.save(address);
	}
	
	public Optional<Address> findAddressById(int id) {
		return addressRepository.findById(id);
	}

	public List<Address> findAllAddresss() {
		return addressRepository.findAll();
	}
	
	
}
