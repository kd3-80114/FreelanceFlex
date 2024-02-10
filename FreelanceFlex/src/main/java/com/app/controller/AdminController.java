package com.app.controller;

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
	

}