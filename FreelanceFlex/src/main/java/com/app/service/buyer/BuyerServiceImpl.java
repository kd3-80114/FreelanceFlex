package com.app.service.buyer;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.app.dao.BuyerDao;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private ModelMapper mapper;
	@Override
	public BuyerDTO findById(Long id) {
		
		return mapper.map(buyerDao.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException
				("Buyer with given id does not exist")),
				BuyerDTO.class) ;	
		}
	@Override
	public BuyerDTO addBuyer(BuyerDTO buyer) {
		
		try {
			Buyer buyerCreated = buyerDao.save(mapper.map(buyer, Buyer.class));
			return mapper.map(buyerCreated, BuyerDTO.class);
		} catch (Exception e) {
			return null;
		}
		} 
		
	
}
