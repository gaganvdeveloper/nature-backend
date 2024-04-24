package com.gbss.nature.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.gbss.nature.entity.User;
import com.gbss.nature.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public User updateUser(User user) {
		return repository.save(user);
	}
	
	public Optional<User> findUserById(int id){
		return repository.findById(id);
	}
	
	public void deleteUserById(int id) {
		repository.deleteById(id);
	}
	
	public List<User> findAllUsers(){
		return repository.findAll();
	}

	public Optional<User> verifyUser(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
	}
	
//	public List<User> findAllByStatus(String status){
//		return repository.findAllActiveUsers(status);
//	}
		
}
