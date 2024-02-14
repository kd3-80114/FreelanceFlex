package com.app.controller;

import java.io.IOException;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.FreelancerDao;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.ApiResponse;
import com.app.entities.Freelancer;
import com.app.service.ImageHandlingService;
import com.app.service.freelancer.FreelanceService;

@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

	@Autowired
	private FreelanceService freelancerService;
	
//	@Autowired
//	private ImageHandlingService imageService;

	@Autowired
	FreelancerDao freelancerDao ;
	
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
	
	//uploadImage
	@PostMapping(value = "/images/{freelancerId}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImage(@PathVariable Long freelancerId, @RequestParam MultipartFile image)
	        throws IOException {
	    System.out.println("In upload image " + freelancerId);
	    return ResponseEntity.status(HttpStatus.CREATED)
	            .body(freelancerService.uploadImage(freelancerId, image)); 
	}

	
	
	//7. download image
		// http://host:port/employees/images/{empId} , method=GET
		@GetMapping(value = "/images/{freelancerId}", produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
		public ResponseEntity<?> downloadImage(@PathVariable long freelancerId) throws IOException {
			System.out.println("in download image " + freelancerId);
			return ResponseEntity.ok(freelancerService.serveImageOfreelancer(freelancerId));
		}
}
