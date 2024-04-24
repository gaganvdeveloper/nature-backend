package com.gbss.nature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbss.nature.entity.Address;
import com.gbss.nature.responsestructure.ResponseStructure;
import com.gbss.nature.service.AddressService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/address")
public class AddressController {

	@Autowired
	AddressService addressService;
	
	@PostMapping("/{id}")
	public ResponseEntity<ResponseStructure<Address>> saveAddress(@PathVariable int id,  @RequestBody Address address) {
		return addressService.saveAddress(id,address);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<ResponseStructure<List<Address>>> findAllAddresss() {
		return addressService.findAllAddress();
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Address>> findAddressById(@PathVariable int id) {
		return addressService.findAddresById(id);
	}
	
	
	
}
