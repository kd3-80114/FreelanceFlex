package com.app.controller;

import static org.springframework.http.MediaType.IMAGE_GIF_VALUE;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

import java.io.IOException;
import java.util.List;

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

import com.app.dto.PaymentDTO;
import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.dto.freelancerdto.FreelancerDTO;

import com.app.entities.Orders;
import com.app.entities.RoleType;
import com.app.service.buyer.BuyerService;

@RestController
@RequestMapping("/buyer")
public class BuyerController {

	@Autowired
	private BuyerService buyerService;
//	@Autowired

	
	@GetMapping("/viewProfile")
	public ResponseEntity<?> viewProfile(@RequestParam Long id)
	{	
		System.out.println(id);

		return ResponseEntity.status(HttpStatus.OK).body(buyerService.findById(id));
	}
	
	//2. add new buyer
	// http://host:port/buyer , method=POST
	@PostMapping("/signUp") 
	public ResponseEntity<?>addNewBuyer(@RequestBody BuyerDTO buyer)
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
	

	//3. update buyer
	// http://host:port/buyer , method=PUT
	
	@PutMapping("/updateBuyer/{buyerId}")
	public ResponseEntity<?>updateBuyer(@PathVariable Long buyerId, @RequestBody BuyerDTO buyer)
	{
		System.out.println("In update Buyer");
		System.out.println();
		
		BuyerDTO retrived = buyerService.updateBuyer(buyerId,buyer);
		if(retrived !=null )
		{

			return ResponseEntity.status(HttpStatus.OK).body(retrived);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(retrived);
		
	}
		
	//4.Buyer reviews a freelancer
	// http://host:port/buyer , method=PUT
	
	@PostMapping("/review/{freelanceId}/{buyerId}")
	public ResponseEntity<?>addReview(@PathVariable Long freelanceId,@PathVariable Long buyerId,@RequestBody ReviewsDTO review )
	{
		ReviewsDTO reviewed = buyerService.addReview(freelanceId,buyerId,review);
		
		if(reviewed !=null ) 
		{

			return ResponseEntity.status(HttpStatus.OK).body(reviewed);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(reviewed);
		
	}
	
	@GetMapping("/viewReview/{buyerId}")
	public ResponseEntity<?> viewReview(@PathVariable Long buyerId) {
		System.out.println("In  view Reviews");	
		return ResponseEntity.status(HttpStatus.OK).body(buyerService.getAllReviews(buyerId));	
	}
	
	//Buyer does payment to a freelancer
	// // http://host:port/buyer , method=PUT
	@PostMapping("/payment/{freelanceId}/{buyerId}")
	public ResponseEntity<?>addPayment(@PathVariable Long freelanceId,@PathVariable Long buyerId,@RequestBody PaymentDTO payment)
	{	System.out.println("In the addPayment");
		PaymentDTO payments = buyerService.addPayment(freelanceId,buyerId,payment);
		if(payments !=null)
		{
			return ResponseEntity.status(HttpStatus.OK).body(payments);
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).body(payments);	
	}
	
	@GetMapping("/viewPayments/{buyerId}")
	public ResponseEntity<?> viewPayments(@PathVariable Long buyerId) {
		System.out.println("In viewPayments");	
		return ResponseEntity.status(HttpStatus.OK).body(buyerService.getAllPayments(buyerId));	
	}
	
	
	

	//4. place new order
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
	//uploadImage
	@PostMapping(value = "/images/{buyerId}", consumes = "multipart/form-data")
	public ResponseEntity<?> uploadImage(@PathVariable Long buyerId, @RequestParam MultipartFile image)
			throws IOException {
		System.out.println("in upload image " + buyerId);
		return ResponseEntity.status(HttpStatus.CREATED).body(buyerService.uploadImage(buyerId, image));
	}
	//downloadImage
	@GetMapping(value = "/images/{buyerId}", produces = { IMAGE_GIF_VALUE, IMAGE_JPEG_VALUE, IMAGE_PNG_VALUE })
	public ResponseEntity<?> downloadImage(@PathVariable long buyerId) throws IOException {
		System.out.println("in download image " + buyerId);
		return ResponseEntity.ok(buyerService.serveImageOfbuyer(buyerId));
	}
}
