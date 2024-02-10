package com.app.service.buyer;

import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;

public interface BuyerService {

	BuyerDTO addBuyer(BuyerDTO buyer);
	
	BuyerDTO findById(Long id);

	BuyerDTO updateBuyer(Long buyerId, BuyerDTO buyer);

	ReviewsDTO addReview(Long freelanceId,Long buyerId,ReviewsDTO review);

}
