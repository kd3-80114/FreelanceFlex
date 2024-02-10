package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.entities.Orders;
import com.app.service.buyer.BuyerService;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;
	
	@GetMapping("/viewProfile")
	public ResponseEntity<?> viewProfile(@RequestParam Long id, @RequestParam String email, @RequestParam String Role)
	{	
		System.out.println(id);

		return ResponseEntity.status(HttpStatus.OK).body(buyerService.findById(id));
	}
	
	//2. add new buyer
	// http://host:port/buyer , method=POST
	@PostMapping 
	public ResponseEntity<?> addNewBuyer(@RequestBody BuyerDTO buyer)
	{
		System.out.println("In add new Buyer/post");
		System.out.println(buyer);
//		return ResponseEntity.status(HttpStatus.OK).body(buyerService.addBuyer(buyer));
		BuyerDTO finalResult =	buyerService.addBuyer(buyer);
		if (finalResult != null) {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(finalResult);
	}
	
	//2. place new order
		// http://host:port/buyer/placeOrder , method=POST
	@PostMapping("/placeOrder")
	public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderDTO order) {
		System.out.println("In add new order/post");
		System.out.println(order);
		PlaceOrderDTO finalResult =	buyerService.createNewOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(finalResult);	
	}
	
	@GetMapping("/viewOrders/{buyerId}")
	public ResponseEntity<?> viewOrders(@PathVariable Long buyerId){
		List<Orders> finalOrderList =	buyerService.getOrderDetails(buyerId);
		if (finalOrderList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(finalOrderList);
		
	}
	
}