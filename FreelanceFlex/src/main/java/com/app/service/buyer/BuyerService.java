package com.app.service.buyer;

import com.app.dto.buyerdto.BuyerDTO;

public interface BuyerService {

	BuyerDTO addBuyer(BuyerDTO buyer);
	
	BuyerDTO findById(Long id);

	BuyerDTO updateBuyer(Long buyerid, BuyerDTO buyer);

}
