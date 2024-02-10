package com.app.service.buyer;
import java.util.List;
import org.springframework.http.ResponseEntity;
import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.entities.Orders;

public interface BuyerService {

	BuyerDTO addBuyer(BuyerDTO buyer);
	
	BuyerDTO findById(Long id);

	BuyerDTO updateBuyer(Long buyerId, BuyerDTO buyer);

	ReviewsDTO addReview(Long freelanceId,Long buyerId,ReviewsDTO review);

	PlaceOrderDTO createNewOrder(PlaceOrderDTO order);

	List<Orders> getOrderDetails(Long buyerId);

}
