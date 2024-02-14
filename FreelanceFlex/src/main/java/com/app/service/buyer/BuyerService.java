package com.app.service.buyer;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.entities.ApiResponse;

public interface BuyerService {

	BuyerDTO addBuyer(BuyerDTO buyer);
	
	BuyerDTO findById(Long id);

	BuyerDTO updateBuyer(Long buyerId, BuyerDTO buyer);

	//ReviewsDTO addReview(Long freelanceId,Long buyerId,ReviewsDTO review);

	PlaceOrderDTO createNewOrder(PlaceOrderDTO order);

	List<ReviewsDTO> getAllReviews(Long buyerId);
	ApiResponse uploadImage(Long id, MultipartFile image)throws IOException;
	public byte[] serveImageOfbuyer(long id);

}
