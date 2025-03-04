package com.app.controller;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
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

import com.app.dao.GigDao;
import com.app.dto.PaymentDTO;
import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.dto.freelancerdto.FreelancerDTO;

import com.app.entities.Gigs;
import com.app.entities.Orders;
import com.app.entities.RoleType;
import com.app.service.buyer.BuyerService;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/buyer")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;
	// @Autowired

	@GetMapping("/viewProfile")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER', 'ROLE_BUYER')")	
	public ResponseEntity<?> viewProfile(@RequestParam Long id) {
		System.out.println(id);

		return ResponseEntity.status(HttpStatus.OK).body(buyerService.findById(id));
	}

	// 2. add new buyer
	// http://host:port/buyer , method=POST
	@PostMapping("/signUp") 
//	@PreAuthorize("hasRole('ROLE_BUYER')")
	public ResponseEntity<?>addNewBuyer(@RequestBody BuyerDTO buyer)
	{
		
		System.out.println("In add new Buyer/post");
		System.out.println(buyer);
		// return
		// ResponseEntity.status(HttpStatus.OK).body(buyerService.addBuyer(buyer));
		BuyerDTO finalResult = buyerService.addBuyer(buyer);
		if (finalResult != null) {
			return ResponseEntity.status(HttpStatus.OK).body(finalResult);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(finalResult);
	}

	// 3. update buyer
	// http://host:port/buyer , method=PUT
	
	@PutMapping("/{buyerId}")
	@PreAuthorize("hasRole('ROLE_BUYER')")
	public ResponseEntity<?>updateBuyer(@PathVariable Long buyerId, @RequestBody BuyerDTO buyer)
	{
		System.out.println("In update Buyer");
		System.out.println();

		BuyerDTO retrived = buyerService.updateBuyer(buyerId, buyer);
		if (retrived != null) {

			return ResponseEntity.status(HttpStatus.OK).body(retrived);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(retrived);

	}
 
	// 4.Buyer reviews a freelancer
	// http://host:port/buyer , method=PUT

	@PostMapping("/review/{freelanceId}/{buyerId}")
	@PreAuthorize("hasRole('ROLE_BUYER')")
	public ResponseEntity<?> addReview(@PathVariable Long freelanceId, @PathVariable Long buyerId,
			@RequestBody ReviewsDTO review) {
		ReviewsDTO reviewed = buyerService.addReview(freelanceId, buyerId, review);
		System.out.println();
		if (reviewed != null) {

			return ResponseEntity.status(HttpStatus.OK).body(reviewed);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(reviewed);

	}

	@GetMapping("/viewReview/{buyerId}")
	@PreAuthorize("hasAnyRole('ROLE_BUYER','ROLE_ADMIN' )")
	public ResponseEntity<?> viewReview(@PathVariable Long buyerId) 
	{
		System.out.println("In  view Reviews");
		return ResponseEntity.status(HttpStatus.OK).body(buyerService.getAllReviews(buyerId));
	}

	// Buyer does payment to a freelancer
	// // http://host:port/buyer , method=PUT
	@PostMapping("/payment/{freelanceId}/{buyerId}")
	@PreAuthorize("hasRole('ROLE_BUYER')")
	public ResponseEntity<?> addPayment(@PathVariable Long freelanceId, @PathVariable Long buyerId,
			@RequestBody PaymentDTO payment) {
		System.out.println("In the addPayment");
		PaymentDTO payments = buyerService.addPayment(freelanceId, buyerId, payment);
		if (payments != null) {
			return ResponseEntity.status(HttpStatus.OK).body(payments);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(payments);
	}

	@GetMapping("/viewPayments/{buyerId}")
	@PreAuthorize("hasAnyRole('ROLE_BUYER','ROLE_ADMIN' )")
	public ResponseEntity<?> viewPayments(@PathVariable Long buyerId) {
		System.out.println("In viewPayments");
		return ResponseEntity.status(HttpStatus.OK).body(buyerService.getAllPayments(buyerId));
	}

	// 4. place new order
	// http://host:port/buyer/placeOrder , method=POST
	@PostMapping("/placeOrder")
	@PreAuthorize("hasRole('ROLE_BUYER')")
	public ResponseEntity<?> placeOrder(@RequestBody PlaceOrderDTO order) {
		System.out.println("In add new order/post");
		System.out.println(order);
		PlaceOrderDTO finalResult =	buyerService.createNewOrder(order);
		return ResponseEntity.status(HttpStatus.CREATED).body(finalResult);	
	}
	

//	@GetMapping("/viewReview/{buyerId}")
//	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
//	public ResponseEntity<?> viewReview(@PathVariable Long buyerId) {
//		System.out.println("In  view Reviews");	
//		return ResponseEntity.status(HttpStatus.OK).body(buyerService.getAllReviews(buyerId));	
//		PlaceOrderDTO finalResult = buyerService.createNewOrder(order);
//		return ResponseEntity.status(HttpStatus.CREATED).body(finalResult);
//	}

	@GetMapping("/viewOrders/{buyerId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_BUYER')")
	public ResponseEntity<?> viewOrders(@PathVariable Long buyerId){
		System.out.println("In buyer biew order");
		List<Orders> finalOrderList =	buyerService.getOrderDetails(buyerId);
		if (finalOrderList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(finalOrderList);

	}

	// uploadImage
	@PostMapping(value = "/images/{buyerId}", consumes = "multipart/form-data")
	@PreAuthorize("hasRole('ROLE_BUYER')")
	public ResponseEntity<?> uploadImage(@PathVariable Long buyerId, @RequestParam MultipartFile image)
			throws IOException {
		System.out.println("in upload image " + buyerId);
		return ResponseEntity.status(HttpStatus.CREATED).body(buyerService.uploadImage(buyerId, image));
	}

	// downloadImage
	@GetMapping(value = "/images/{buyerId}", produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	@PreAuthorize("hasRole('ROLE_BUYER')")
	public ResponseEntity<?> downloadImage(@PathVariable long buyerId) throws IOException {
		System.out.println("in download image " + buyerId);
		return ResponseEntity.ok(buyerService.serveImageOfbuyer(buyerId));
	}

	@GetMapping("/viewGigs/{freelancerId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER', 'ROLE_BUYER')")	
	public ResponseEntity<?> viewGigs(@PathVariable Long freelancerId) {
		List<Gigs> freelancerGigs = buyerService.getAllGigs(freelancerId);

		return ResponseEntity.status(HttpStatus.OK).body(freelancerGigs);
	}
	@GetMapping("/viewAllGigs")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER', 'ROLE_BUYER')")	
	public ResponseEntity<?> viewAllGigs() {
		List<Gigs> gigs = buyerService.getEveryGigs();

		return ResponseEntity.status(HttpStatus.OK).body(gigs);
	}
	@GetMapping("/findFreelancerId/{gigsId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER', 'ROLE_BUYER')")	
	public ResponseEntity<?> findFreelancerIdByGigsId(@PathVariable Long gigsId) {
		Long fid=buyerService.findFreelancerById(gigsId);
		return ResponseEntity.status(HttpStatus.OK).body(fid);
	}

	@GetMapping("/findFreelancerByReviewId/{buyerId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER', 'ROLE_BUYER')")	
	public ResponseEntity<?> findFreelancerIdByReviewId(@PathVariable Long buyerId) {
		List<Long> freelancerId=buyerService.findFreelancerByBuyerId(buyerId);
		return ResponseEntity.status(HttpStatus.OK).body(freelancerId);
	}
	
	@GetMapping("/findFreelancerByOrderId/{orderId}")
	@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_FREELANCER', 'ROLE_BUYER')")	
	public ResponseEntity<?> findFreelancerIdByOrderId(@PathVariable Long orderId) {
		Long freelancerId=buyerService.findFreelancerByOrderId(orderId);
		return ResponseEntity.status(HttpStatus.OK).body(freelancerId);
	}
}
