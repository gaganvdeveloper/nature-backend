package com.gbss.nature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gbss.nature.dto.AuthUser;
import com.gbss.nature.entity.User;
import com.gbss.nature.responsestructure.ResponseStructure;
import com.gbss.nature.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "http://localhost:3000" , allowedHeaders = "*")
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	UserService userService;
	
	@Operation(description = "To Login By Using Email and Password as JSON Object", summary = "User Login by Emial & Password")
	@ApiResponses(value = {@ApiResponse(description = "Login Successfull", responseCode = "200"),@ApiResponse(description = "Unable to Login",responseCode = "404")})
	@PostMapping("/login")
	public ResponseEntity<ResponseStructure<User>> verifyUser(@RequestBody AuthUser authUser) {
		System.out.println("Calling Login...");
		return userService.verifyUser(authUser);
	}

	@Operation(description = "User Will be Created by this end point but User Object should be sent in JSON Fromat", summary = "To Create User")
	@ApiResponses(value = {@ApiResponse(description = "User Saved Successfully...", responseCode = "200"),@ApiResponse(description = "Unable to Save User",responseCode = "404")})
	@PostMapping
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
		return userService.saveUser(user);
	}

	@Operation(description = "To Update User By Sending Updated User JSON Object as Argument ", summary = "To Update User")
	@ApiResponses(value = {@ApiResponse(description = "User Saved Successfully...", responseCode = "200"),@ApiResponse(description = "Unable to Save User",responseCode = "404")})
	@PutMapping
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		return userService.updateUser(user);
	}
	
	@Operation(description = "To Fetch A User by sending the User id as Pathvaribale ", summary = "To Fetch User By Id")
	@ApiResponses(value = {@ApiResponse(description = "User Saved Successfully...", responseCode = "200"),@ApiResponse(description = "Unable to Save User",responseCode = "404")})
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable int id){
		return userService.findUserById(id);
	}
	
	@Operation(description = "To Delete User from Databse Permanenty by sending id as Pathvariable as argument", summary = "To Delete User by Id")
	@ApiResponses(value = {@ApiResponse(description = "User Saved Successfully...", responseCode = "200"),@ApiResponse(description = "Unable to Save User",responseCode = "404")})
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteUserById(@PathVariable int id){
		return userService.deleteUserById(id);
	}
	
	@Operation(description = "To Fetch All Available Users from the Database", summary = "Fetch All Users")
	@ApiResponses(value = {@ApiResponse(description = "All Users Found Successfully", responseCode = "200"),@ApiResponse(description = "No Users Present in Database",responseCode = "200")})
	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<User>>> findAll(){
		return userService.findAllUsers();
	}
	
}
