package com.gbss.nature.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gbss.nature.dao.UserDao;
import com.gbss.nature.dto.AuthUser;
import com.gbss.nature.entity.User;
import com.gbss.nature.exceptionclasses.InvalidUserIdException;
import com.gbss.nature.exceptionclasses.NoUserFoundException;
import com.gbss.nature.responsestructure.ResponseStructure;

@Service
public class UserService {

	@Autowired
	UserDao userDao;

	public ResponseEntity<ResponseStructure<User>> saveUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("User Saved Successfully...");
		structure.setBody(userDao.saveUser(user));
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> updateUser(User user) {
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("User Updated Successfully...");
		structure.setBody(userDao.saveUser(user));
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> findUserById(int id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty())
			throw new InvalidUserIdException();
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("User Found Successfully...");
		structure.setBody(optional.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> deleteUserById(int id) {
		Optional<User> optional = userDao.findUserById(id);
		if (optional.isEmpty())
			throw new InvalidUserIdException();
		userDao.deleteUserById(id);
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("User Deleted Successfully...");
		structure.setBody("Delete Completed...");
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<User>>> findAllUsers() {
		List<User> users = userDao.findAllUsers();
		if (users.isEmpty())
			throw new NoUserFoundException();
		ResponseStructure<List<User>> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("All Users Found Successfully...");
		structure.setBody(users);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<User>> verifyUser(AuthUser authUser) {
		Optional<User> optional = userDao.verifyUser(authUser.getEmail(), authUser.getPassword());
		if(optional.isEmpty())
			throw new InvalidUserIdException();
		ResponseStructure<User> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Login Successfull...");
		structure.setBody(optional.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

}
