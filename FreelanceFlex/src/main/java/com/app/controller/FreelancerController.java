package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SignInDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.Orders;
import com.app.entities.RoleType;
import com.app.service.freelancer.FreelanceService;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

	@Autowired
	private FreelanceService freelancerService;
	
	@GetMapping("/viewProfile")
	public ResponseEntity<?> viewProfile(@RequestParam Long id)
//	(@RequestParam /*@Valid*/ FreelancerProfileDTO freelancer)
	{	
		
//		System.out.println(freelancer.getId());
		System.out.println(id);

//		return ResponseEntity.status(HttpStatus.OK).body(freelanceService.findById(freelancer.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(freelancerService.findById(id));
		
	}
	//1. add new freelancer 
	// http://host:port/freelancer , method=POST
	@PostMapping("/signUp")
	public  ResponseEntity<?> addNewFreelance(@RequestBody FreelancerDTO freelancer)
	{
//		freelancer.getSignIn().setEmail(freelancer.getEmail());
//		freelancer.getSignIn().setPassword(freelancer.getPassword());
//		freelancer.getSignIn().setRole(RoleType.FREELANCER);
		System.out.println("In add new Freelancer/post");
		System.out.println("freelancer signIN dto="+freelancer.getSignIn());
		System.out.println("freelancer="+freelancer);
		FreelancerDTO finalResult =	freelancerService.addFreelancer(freelancer);
		System.out.println(finalResult);
		if (finalResult != null) {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(finalResult);
	}
	//2.Create gig .
	//http://host:port/freelancer/creategig,method=POST
	@PostMapping("/createGig")
	public  ResponseEntity<?> createNewGig(@RequestBody GigDTO gig)
	{
		
		System.out.println("In add new Freelancer/post/CreateGIg");
		System.out.println(gig.getFreelancer().getId());
		GigDTO finalResult =	freelancerService.addNewGig(gig);
		System.out.println(finalResult);
		if (finalResult != null) {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(finalResult);
	}
	
	@PutMapping("/{freelanceId}")
	public  ResponseEntity<?> updateFreelance(@PathVariable Long freelanceId,@RequestBody FreelancerDTO freelancer)
	{
		System.out.println("In update Freelancer/put");
		System.out.println(freelancer);
		FreelancerDTO finalResult =	freelancerService.updateFreelancer(freelanceId,freelancer);
		System.out.println(finalResult);
		if (finalResult != null) {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(finalResult);
	}

	@GetMapping("/{freelancerId}")
	public ResponseEntity<?> viewReview(@PathVariable Long freelancerId) {
		System.out.println("In  view Reviews");	
		return ResponseEntity.status(HttpStatus.OK).body(freelancerService.getAllReviews(freelancerId));	

	}
	@GetMapping("/viewOrders/{freelancerId}")
	public ResponseEntity<?> viewOrders(@PathVariable Long freelancerId){
		List<Orders> finalOrderList =	freelancerService.getOrderDetails(freelancerId);
		if (finalOrderList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(finalOrderList);	
	}
}
