package com.app.service.buyer;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.entities.Address;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.app.entities.Reviews;
import com.app.dao.BuyerDao;
import com.app.dao.FreelancerDao;
import com.app.dao.ReviewDao;

@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {

	@Autowired
	private BuyerDao buyerDao;
	@Autowired 
	private FreelancerDao freelancerDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired 
	private ReviewDao reviewDao;
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
	public BuyerDTO updateBuyer(Long buyerId, BuyerDTO buyer) {
		// TODO Auto-generated method stub
		
		Buyer updatedBuyer = buyerDao.findById(buyerId).orElseThrow(()->new ResourceNotFoundException("Buyer with given id does not exist"));
		updatedBuyer.setFirstName(buyer.getFirstName());
		updatedBuyer.setLastName(buyer.getLastName());
		updatedBuyer.setEmail(buyer.getEmail());
		updatedBuyer.setContactNo(buyer.getContactNo());
		updatedBuyer.setDescription(buyer.getDescription());
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
	@Override
	public ReviewsDTO addReview(Long freelanceId ,Long buyerId ,ReviewsDTO review) {

	
		try {
		    Freelancer freelancer =freelancerDao.findById(freelanceId).orElseThrow(()->new ResourceNotFoundException("Freelancer with given id does not exist"));	   
		    Reviews reviewCreated = reviewDao.save(mapper.map(review, Reviews.class));	
			reviewCreated.setFreelancer(freelancer);
			
			Buyer buyer =  reviewDao.findById(buyerId).orElseThrow(()->new ResourceNotFoundException("Buyer with id not found"));
			reviewCreated.setBuyer(buyer);
			System.out.println(reviewCreated);
			return mapper.map(reviewCreated, ReviewsDTO.class);
	
		}catch (Exception e) 
		{
			System.out.println("before null");
			return null;
		}
	}
	
}



		
				


