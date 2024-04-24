package com.gbss.nature.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gbss.nature.dao.AddressDao;
import com.gbss.nature.dao.UserDao;
import com.gbss.nature.entity.Address;
import com.gbss.nature.entity.User;
import com.gbss.nature.exceptionclasses.InvalidAddressIdException;
import com.gbss.nature.exceptionclasses.InvalidEmailException;
import com.gbss.nature.exceptionclasses.InvalidUserIdException;
import com.gbss.nature.responsestructure.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	EmailService emailService;

	@Autowired
	AddressDao addressDao;

	@Autowired
	UserDao userDao;
	
	public ResponseEntity<ResponseStructure<Address>> saveAddress(int id, Address address) {
		
		Optional<User> optional =userDao.findUserById(id);
		if(optional.isEmpty())
			throw new InvalidUserIdException();
		User user = optional.get();
		address = addressDao.saveAddress(address);
		List<Address> addresses = user.getAddresses();
		if(addresses==null)
			addresses = new ArrayList<>();
		addresses.add(address);
		user.setAddresses(addresses);
		user=userDao.updateUser(user);
		ResponseStructure<Address> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Address Saved Successfully...");
		structure.setBody(address);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Address>>> findAllAddress() {
		ResponseStructure<List<Address>> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("All Address Found SUccessfully");
		structure.setBody(addressDao.findAllAddresss());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Address>> findAddresById(int id) {
		Optional<Address> optional = addressDao.findAddressById(id);
		if (optional.isEmpty())
			throw new InvalidAddressIdException();
		ResponseStructure<Address> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Address Found Successfully...");
		structure.setBody(optional.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

}
