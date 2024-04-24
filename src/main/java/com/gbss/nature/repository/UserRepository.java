package com.gbss.nature.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gbss.nature.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	List<User> findByStatus(String status);

	Optional<User> findByEmailAndPassword(String email, String password);
	
}
