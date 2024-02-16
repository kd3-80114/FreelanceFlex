package com.app.controller;

import java.io.IOException;
import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.app.dto.SignInDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.Gigs;
import com.app.entities.Orders;
import com.app.entities.RoleType;
import com.app.service.freelancer.FreelanceService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/freelancer")
public class FreelancerController {

	@Autowired
	private FreelanceService freelancerService;

	@Autowired
	FreelancerDao freelancerDao;
 
	@GetMapping("/viewProfile")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER', 'ROLE_BUYER' )")
	public ResponseEntity<?> viewProfile(@RequestParam Long id)
	// (@RequestParam /*@Valid*/ FreelancerProfileDTO freelancer)
	{

		// System.out.println(freelancer.getId());
		System.out.println(id);

		// return
		// ResponseEntity.status(HttpStatus.OK).body(freelanceService.findById(freelancer.getId()));
		return ResponseEntity.status(HttpStatus.OK).body(freelancerService.findById(id));

	}

	// 1. add new freelancer
	// http://host:port/freelancer , method=POST
	@PostMapping("/signUp")
	
	public  ResponseEntity<?> addNewFreelance(@RequestBody FreelancerDTO freelancer)
	{
//		freelancer.getSignIn().setEmail(freelancer.getEmail());
//		freelancer.getSignIn().setPassword(freelancer.getPassword());
//		freelancer.getSignIn().setRole(RoleType.FREELANCER);
		System.out.println("In add new Freelancer/post");
		System.out.println("freelancer signIN dto=" + freelancer.getSignIn());
		System.out.println("freelancer=" + freelancer);
		FreelancerDTO finalResult = freelancerService.addFreelancer(freelancer);
		System.out.println(finalResult);
		if (finalResult != null) {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(finalResult);
	}

	// 2.Create gig .
	// http://host:port/freelancer/creategig,method=POST
	@PostMapping("/createGig")
	@PreAuthorize("hasRole('ROLE_FREELANCER')")
	public  ResponseEntity<?> createNewGig(@RequestBody GigDTO gig)
	{
		
		System.out.println("In add new Freelancer/post/CreateGIg");
		System.out.println(gig.getFreelancer().getId());
		GigDTO finalResult = freelancerService.addNewGig(gig);
		System.out.println(finalResult);
		if (finalResult != null) {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(finalResult);
	}

	@PutMapping("/{freelanceId}")
	@PreAuthorize("hasRole('ROLE_FREELANCER')")
	public  ResponseEntity<?> updateFreelance(@PathVariable Long freelanceId,@RequestBody FreelancerDTO freelancer)
	{
		System.out.println("In update Freelancer/put");
		System.out.println(freelancer);
		FreelancerDTO finalResult = freelancerService.updateFreelancer(freelanceId, freelancer);
		System.out.println(finalResult);
		if (finalResult != null) {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(finalResult);
	}


	@GetMapping("/viewReview/{freelancerId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
	public ResponseEntity<?> viewReview(@PathVariable Long freelancerId) {
		System.out.println("In  view Reviews");
		return ResponseEntity.status(HttpStatus.OK).body(freelancerService.getAllReviews(freelancerId));

	}

	@GetMapping("/viewPayments/{freelancerId}")
	public ResponseEntity<?> viewPayment(@PathVariable Long freelancerId) {
		System.out.println("In  view Payment");
		return ResponseEntity.status(HttpStatus.OK).body(freelancerService.getAllPayments(freelancerId));

	}

	@GetMapping("/viewOrders/{freelancerId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER')")
	public ResponseEntity<?> viewOrders(@PathVariable Long freelancerId){
		List<Orders> finalOrderList =	freelancerService.getOrderDetails(freelancerId);
		if (finalOrderList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(finalOrderList);
	}

	@GetMapping("viewGigs/{freelancerId}")
		@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER', 'ROLE_BUYER')")
	public ResponseEntity<?> viewGigs(@PathVariable Long freelancerId) {
		List<Gigs> freelancerGigs = freelancerService.getAllGigs(freelancerId);

		return ResponseEntity.status(HttpStatus.OK).body(freelancerGigs);
	}

	// uploadImage
	@PostMapping(value = "/images/{freelancerId}", consumes = "multipart/form-data")
			@PreAuthorize("hasRole('ROLE_FREELANCER')")
	public ResponseEntity<?> uploadImage(@PathVariable Long freelancerId, @RequestParam MultipartFile image)
			throws IOException {
		System.out.println("In upload image " + freelancerId);
		System.out.println(image);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(freelancerService.uploadImage(freelancerId, image));
	}
	// uploadGigsImage
		@PostMapping(value = "/gigs/images/{freelancerId}/{gigsid}", consumes = "multipart/form-data")
				@PreAuthorize("hasRole('ROLE_FREELANCER')")
		public ResponseEntity<?> uploadGigImage(@PathVariable Long freelancerId, @RequestParam MultipartFile image,@PathVariable int gigsid)
				throws IOException {
			System.out.println("In upload image " + freelancerId);
			System.out.println("In upload image " + gigsid);
			System.out.println(image);
			return ResponseEntity.status(HttpStatus.CREATED)
					.body(freelancerService.uploadGigsImage(freelancerId, image,gigsid));
		}
		
		@PreAuthorize("hasRole('ROLE_FREELANCER')")
		@GetMapping(value = "/gigs/images/{gigsId}", produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
		public ResponseEntity<?> downloadGigsImage(@PathVariable long gigsId) throws IOException {
			System.out.println("in download image " + gigsId);
			return ResponseEntity.ok(freelancerService.saveGigImage(gigsId));
		}
	

	// 7. download image

	@PreAuthorize("hasRole('ROLE_FREELANCER')")
	@GetMapping(value = "/images/{freelancerId}", produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> downloadImage(@PathVariable long freelancerId) throws IOException {
		System.out.println("in download image " + freelancerId);
		return ResponseEntity.ok(freelancerService.serveImageOfreelancer(freelancerId));
	}
}
