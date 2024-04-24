package com.gbss.nature.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.gbss.nature.dao.AddressDao;
import com.gbss.nature.dao.OrdersDao;
import com.gbss.nature.dao.ProductDao;
import com.gbss.nature.entity.Address;
import com.gbss.nature.entity.Orders;
import com.gbss.nature.entity.Product;
import com.gbss.nature.exceptionclasses.InvalidAddressIdException;
import com.gbss.nature.exceptionclasses.InvalidEmailException;
import com.gbss.nature.exceptionclasses.InvalidOrderIdException;
import com.gbss.nature.exceptionclasses.OrdersCreationException;
import com.gbss.nature.responsestructure.ResponseStructure;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrdersService {

	@Autowired
	OrdersDao ordersDao;

	@Autowired
	AddressDao addressDao;

	@Autowired
	EmailService emailService;
	
	@Autowired
	ProductDao productDao;

	public ResponseEntity<ResponseStructure<Orders>> createOrders(HashMap<String, Object> orders) {
		System.out.println("HI");
		System.out.println(orders.get("products"));
		System.out.println("HI");
		double amount = Integer.parseInt(orders.get("amount").toString());
		RazorpayClient client = null;
		Order order = null;
		try {
			client = new RazorpayClient("rzp_test_Dzmf3NNZ4kXWo0", "Yi1hO8bKsbuBGL3N7Gj7V6n6");
			JSONObject ob = new JSONObject();
			ob.put("amount", amount * 100);
			ob.put("currency", orders.get("currency"));
			ob.put("receipt", orders.get("receipt"));
			order = client.Orders.create(ob);
		} catch (RazorpayException e) {
			throw new OrdersCreationException();
		}
		Orders orders2 = new Orders();
		orders2.setId(order.get("id"));
		orders2.setEntity(order.get("entity"));
		orders2.setAmount(order.get("amount"));
		orders2.setAmount_paid(order.get("amount_paid"));
		orders2.setAmount_due(order.get("amount_due"));
		orders2.setCurrency(order.get("currency"));
		orders2.setReceipt(order.get("receipt"));
//		orders2.setOffer_id(order.get("offer_id"));
		orders2.setStatus(order.get("status"));
		orders2.setAttempts(order.get("attempts"));
//		orders2.setNotes(order.get("notes"));
		orders2.setCreated_at(order.get("created_at"));
		
		
		
		List<Product> products = orders2.getProducts();
		
		
		for(Product p :products)
		{
			 Optional<Product> existingProduct = productDao.findProductById(p.getId());
			 if(existingProduct.isEmpty())
				 throw new RuntimeException("Unable To Find Product, Invalid Id in Orders Service 76");
			 products.add(existingProduct.get());
		}
		orders2.setProducts(products);
		System.out.println(orders2);
		ResponseStructure<Orders> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Order Created Successfully....");
		structure.setBody(ordersDao.createOrders(orders2));
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Orders>> createOrders(Orders orders) {
		ResponseStructure<Orders> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Order Updated Successfully....");
		structure.setBody(ordersDao.updateOrders(orders));
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Orders>> findOrdersById(String id) {
		Optional<Orders> optional = ordersDao.findOrdersById(id);
		if (optional.isEmpty())
			throw new InvalidEmailException();
		ResponseStructure<Orders> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Order Created Successfully....");
		structure.setBody(optional.get());
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<String>> orderConfirmEmail(String oid, int aid) {
		Optional<Orders> optional1 = ordersDao.findOrdersById(oid);
		Optional<Address> optional2 = addressDao.findAddressById(aid);
		System.out.println(aid);
		if (optional2.isEmpty())
			throw new InvalidAddressIdException();
		if (optional1.isEmpty())
			throw new InvalidOrderIdException();
		Orders orders = optional1.get();
		Address address2 = optional2.get();
		System.out.println(address2);
		orders.setAddress(address2);
		ordersDao.updateOrders(orders);
		emailService.sendSimpleEmail(address2.getEmail(), "Order Palced Confirmation...",
				"Dear " + address2.getCustomerName()
						+ " Your Order has been Placed Successfully... Order Id is "+orders.getId()+" Your Total Paid Amount is  "
						+ (orders.getAmount() / 100) + " Your Order will be Deliverd in next 2-3  Days ");
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("Order Placed Confirmation mail Sent...");
		structure.setBody("Order Placed...");
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Orders>>> findAllOrders() {
		List<Orders> orders = ordersDao.findAllOrders();
		ResponseStructure<List<Orders>> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("All Orders Fetched Successfully...");
		structure.setBody(orders);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<List<Orders>>> findAllActiveOrders() {
		List<Orders> orders = ordersDao.findAllOrders();
		List<Orders> activeOrders=null;
		if(!orders.isEmpty()) {
			activeOrders = orders.stream().filter(o->o.getStatus().equalsIgnoreCase("active")).collect(Collectors.toList());
		}
		ResponseStructure<List<Orders>> structure = new ResponseStructure<>();
		structure.setCode(HttpStatus.OK.value());
		structure.setMessage("All Active Orders Fetched Successfully...");
		structure.setBody(activeOrders);
		return new ResponseEntity<>(structure, HttpStatus.OK);
	}


}
