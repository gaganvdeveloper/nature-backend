package com.gbss.nature.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CheckController {
	
	@Operation(description = "To Check The Controller is Working or not which returns Hello From Spring Boot Application", summary = "To Check Application is working or not")
	@ApiResponses(value = {@ApiResponse(description = "Hello From Spring Boot Application...", responseCode = "200"),@ApiResponse(description = "404 Not Found / Bad Request",responseCode = "404")})
	@GetMapping(value = "/hi")
	public String hello() {
		return "Hello From Spring Boot Application...";
	}
	
	@Operation(description = "To Check The Controller is Working or not which returns Hello From Spring Boot Application", summary = "To Check Application is working or not")
	@ApiResponses(value = {@ApiResponse(description = "Spring Boot Application Say's Hi...", responseCode = "200"),@ApiResponse(description = "404 Not Found / Bad Request",responseCode = "404")})
	@GetMapping
	public String hi() {
		return "Spring Boot Application Say's Hi...";
	}
	
	
	
	
}
