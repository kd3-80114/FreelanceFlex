package com.app.service.buyer;

import java.io.IOException;
import java.util.List;
import java.util.List;
import org.springframework.http.ResponseEntity;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.entities.ApiResponse;
import com.app.dto.PaymentDTO;
import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.entities.Gigs;
import com.app.entities.Orders;

public interface BuyerService {

	BuyerDTO addBuyer(BuyerDTO buyer);

	BuyerDTO findById(Long id);

	BuyerDTO updateBuyer(Long buyerId, BuyerDTO buyer);

	ReviewsDTO addReview(Long freelanceId, Long buyerId, ReviewsDTO review);

	PlaceOrderDTO createNewOrder(PlaceOrderDTO order);

	List<ReviewsDTO> getAllReviews(Long buyerId);

	ApiResponse uploadImage(Long id, MultipartFile image) throws IOException;

	public byte[] serveImageOfbuyer(long id);

	List<Orders> getOrderDetails(Long buyerId);

	List<Gigs> getAllGigs(Long freelancerId);

	PaymentDTO addPayment(Long freelanceId, Long buyerId, PaymentDTO payment);

	List<PaymentDTO> getAllPayments(Long buyerId);

}
