package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.entities.Orders;
import com.app.service.admin.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@GetMapping("/viewProfile")
	public ResponseEntity<?> viewProfile(@RequestParam Long id, @RequestParam String email, @RequestParam String Role) {
		System.out.println(id);
		return ResponseEntity.status(HttpStatus.OK).body(adminService.findById(id));
	}

	
	@PostMapping("/findBuyerProfile")
	public ResponseEntity<?> findBuyerProfileByEmail(@RequestParam String email)
	{
		System.out.println("Inside findProfile by email of Buyer");
		System.out.println(email);
		return ResponseEntity.status(HttpStatus.OK).body(adminService.findBuyerByEmail(email));	
	}
	
	@PostMapping("/findFreelancerProfile")
	public ResponseEntity<?> findFreelancerProfileByEmail(@RequestParam String email)
	{
		System.out.println("Inside findProfile by email of Freelancer");
		System.out.println(email);
		return ResponseEntity.status(HttpStatus.OK).body(adminService.findFreelancerByEmail(email));	
	}
	
	@DeleteMapping("/deleteFreelancer/{freelancerId}")
	public ResponseEntity<?> deleteFreelancer(@PathVariable Long freelancerId){
		System.out.println("in delete freelancer");
		System.out.println(freelancerId);
		return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteFreelancer(freelancerId));
	}
	

	@DeleteMapping("/deleteBuyer/{buyerId}")
	public ResponseEntity<?> deleteBuyer(@PathVariable Long buyerId){
		System.out.println("in delete buyer");
		System.out.println(buyerId);
		return ResponseEntity.status(HttpStatus.OK).body(adminService.deleteBuyer(buyerId));
	}
	
	@GetMapping("/viewFreelancerOrders/{freelancerId}")
	public ResponseEntity<?> getFreelancerOrders(@PathVariable Long freelancerId){
		System.out.println("in admin view order freelancer");
		System.out.println(freelancerId);
		
		List<Orders> finalOrderList =	adminService.getFreelancerOrders(freelancerId);
		if (finalOrderList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(finalOrderList);
	}
	
	@GetMapping("/viewBuyerOrders/{buyerId}")
	public ResponseEntity<?> getBuyerOrders(@PathVariable Long buyerId){
		System.out.println("in admin view order buyer");
		System.out.println(buyerId);
		
		List<Orders> finalOrderList = adminService.getBuyerOrders(buyerId);
		if (finalOrderList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(finalOrderList);
	}
	
	@GetMapping("/blockFreelancer/{freelancerId}")
	public ResponseEntity<?> blockFreelancer(@PathVariable Long freelancerId){
		System.out.println("in admin block freelancer");
		System.out.println(freelancerId);
		
		String finalResult = adminService.getFreelancer(freelancerId);
		if (finalResult == "Blocked") {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(finalResult);
	}
}