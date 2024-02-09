package com.app.service.buyer;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.entities.Address;
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
	@Override
	public BuyerDTO updateBuyer(Long buyerid, BuyerDTO buyer) {
		// TODO Auto-generated method stub
		
		Buyer updatedBuyer = buyerDao.findById(buyerid).orElseThrow(()->new ResourceNotFoundException("Buyer with given id does not exist"));
		updatedBuyer.setFirstName(buyer.getFirstName());
		updatedBuyer.setLastName(buyer.getLastName());
		updatedBuyer.setEmail(buyer.getEmail());
		updatedBuyer.setContactNo(buyer.getContactNo());
		updatedBuyer.setDescription(buyer.getDescription());
		updatedBuyer.setPassword(buyer.getPassword());
		updatedBuyer.setProfilePicture(buyer.getProfilePicture());
		//Address updation
		Address address = updatedBuyer.getPermanentAddress(); // 
		address.setCity(buyer.getPermanentAddress().getCity());
		
		address.setCountry(buyer.getPermanentAddress().getCountry());
		
		address.setLandmark(buyer.getPermanentAddress().getLandmark());
		
		address.setPincode(buyer.getPermanentAddress().getPincode());
		
		address.setState(buyer.getPermanentAddress().getState());
		
		return mapper.map(updatedBuyer, BuyerDTO.class);
	} 			
}

