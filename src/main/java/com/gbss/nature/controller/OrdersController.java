package com.gbss.nature.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gbss.nature.entity.Orders;
import com.gbss.nature.responsestructure.ResponseStructure;
import com.gbss.nature.service.OrdersService;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/order")
public class OrdersController {

	@Autowired
	OrdersService ordersService;

	@Operation(description = "Order will be created By Razorpay and saved to Database and given back as JSON Object as Response to caller", summary = "To Create Order")
	@ApiResponses(value = { @ApiResponse(description = "Order Created Successfully...", responseCode = "200"),
			@ApiResponse(description = "Unable to Save User", responseCode = "404") })
	@PostMapping(value = "/create")
	public ResponseEntity<ResponseStructure<Orders>> createOrder(@RequestBody HashMap<String, Object> orders) {
		return ordersService.createOrders(orders);
	}

	@Operation(description = "To Fetch Order By Sending Order Id as Path Variable", summary = "Fetch Order By Id")
	@ApiResponses(value = { @ApiResponse(description = "Order Created Successfully...", responseCode = "200"),
			@ApiResponse(description = "Unable to Save User", responseCode = "404") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseStructure<Orders>> findOrdersById(@PathVariable String id) {
		return ordersService.findOrdersById(id);
	}

	@Operation(description = "To Fetch All Products Available in the Databse ", summary = "Fetch All ")
	@ApiResponses(value = { @ApiResponse(description = "Order Fetched", responseCode = "200"),
			@ApiResponse(description = "Unable to Fetch Orders", responseCode = "404") })
	@GetMapping(value = "/all")
	public ResponseEntity<ResponseStructure<List<Orders>>> findAllOrders() {
		return ordersService.findAllOrders();
	}

	@GetMapping(value = "/all/active")
	public ResponseEntity<ResponseStructure<List<Orders>>> fetchAllActiveOrders() {
		return ordersService.findAllActiveOrders();
	}

	@Operation(description = "To Get Confirmation Email By Sending Order Id and Address ID as Path variable", summary = "To Get Order Comfirmation Email")
	@ApiResponses(value = { @ApiResponse(description = "Order Created Successfully...", responseCode = "200"),
			@ApiResponse(description = "Unable to Save User", responseCode = "404") })
	@PostMapping(value = "/confirm_email/{oid}/{aid}")
	public ResponseEntity<ResponseStructure<String>> orderComfirmEmail(@PathVariable String oid,
			@PathVariable int aid) {
		return ordersService.orderConfirmEmail(oid, aid);
	}

	@Hidden
	@Operation(description = "Order will be created By Razorpay and saved to Database and given back as JSON Object as Response to caller", summary = "To Create Order")
	@ApiResponses(value = { @ApiResponse(description = "Order Created Successfully...", responseCode = "200"),
			@ApiResponse(description = "Unable to Save User", responseCode = "404") })
	@PostMapping("/paymentverification")
	public String paymentVerification(@RequestParam String razorpay_payment_id, @RequestParam String razorpay_order_id,
			@RequestParam String razorpay_signature) {
		System.out.println("*****************************");
		System.out.println(razorpay_order_id);
		System.out.println(razorpay_payment_id);
		System.out.println(razorpay_signature);
		System.out.println("*****************************");
		return "Successfully Completed....";
	}

}
