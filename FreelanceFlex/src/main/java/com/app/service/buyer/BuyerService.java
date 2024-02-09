package com.app.service.buyer;

import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.buyerdto.PlaceOrderDTO;

public interface BuyerService {

	BuyerDTO findById(Long id);

	BuyerDTO addBuyer(BuyerDTO buyer);

	PlaceOrderDTO createNewOrder(PlaceOrderDTO order);

}
