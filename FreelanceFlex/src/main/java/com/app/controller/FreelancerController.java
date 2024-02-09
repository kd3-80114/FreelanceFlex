package com.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.service.freelancer.FreelanceService;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

	@Autowired
	private FreelanceService freelancerService;
	
	@GetMapping("/viewProfile")
	public ResponseEntity<?> viewProfile(@RequestParam Long id, @RequestParam String email, @RequestParam String Role)
//	(@RequestParam /*@Valid*/ FreelancerProfileDTO freelancer)
	{	
		
//		System.out.println(freelancer.getId());
		System.out.println(id);

//		return ResponseEntity.status(HttpStatus.OK).body(freelanceService.findById(freelancer.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(freelancerService.findById(id));
		
	}
	//1. add new freelancer 
	// http://host:port/freelancer , method=POST
	@PostMapping 
	public  ResponseEntity<?> addNewFreelance(@RequestBody FreelancerDTO freelancer)
	{
		System.out.println("In add new Freelancer/post");
		System.out.println(freelancer);
		FreelancerDTO finalResult =	freelancerService.addFreelancer(freelancer);
		System.out.println(finalResult);
		if (finalResult != null) {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(finalResult);
	}
	
}
