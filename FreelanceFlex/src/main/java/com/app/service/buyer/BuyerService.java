package com.app.service.buyer;

import com.app.dto.buyerdto.BuyerDTO;

public interface BuyerService {

	BuyerDTO findById(Long id);

	BuyerDTO addBuyer(BuyerDTO buyer);

}
