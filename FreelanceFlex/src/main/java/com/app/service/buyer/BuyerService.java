package com.app.service.buyer;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;
import com.app.entities.Orders;

public interface BuyerService {

	BuyerDTO findById(Long id);

	BuyerDTO addBuyer(BuyerDTO buyer);

	PlaceOrderDTO createNewOrder(PlaceOrderDTO order);

	List<Orders> getOrderDetails(Long buyerId);

}
