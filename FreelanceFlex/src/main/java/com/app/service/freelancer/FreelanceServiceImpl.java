package com.app.service.freelancer;
import java.util.List;

import java.util.stream.Collectors;


import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.FreelancerDao;
import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dao.GigDao;
import com.app.dao.ReviewDao;
import com.app.dao.OrderDao;
import com.app.dto.freelancerdto.FreelancerDTO;

import com.app.entities.Address;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.app.entities.Skills;

import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.Freelancer;
import com.app.entities.Gigs;

import com.app.entities.Reviews;

import com.app.entities.Orders;


@Service
@Transactional
public class FreelanceServiceImpl implements FreelanceService {

	@Autowired
	private ReviewDao reviewDao;
	@Autowired
	private FreelancerDao freelancerDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private GigDao gigDao;
	@Override
	public FreelancerDTO findById(Long id) 
	{
		
		return mapper.map(freelancerDao.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException
				("Freelancer with given id does not exist")),
				FreelancerDTO.class) ; 
	}
	@Override
	public FreelancerDTO addFreelancer(FreelancerDTO freelancer) {
		try {
			Freelancer freelancerCreated = freelancerDao.save(mapper.map(freelancer, Freelancer.class));
			return mapper.map(freelancerCreated, FreelancerDTO.class);
	    } catch (Exception e) {
	    	e.printStackTrace();
		}
		return null;

	}
	@Override

	public FreelancerDTO updateFreelancer(Long freelanceId ,FreelancerDTO freelancer) {
		Freelancer freelancerUpdated = freelancerDao.findById(freelanceId).orElseThrow(() -> new ResourceNotFoundException("Invalid Dept Id!!!"));
		freelancerUpdated.setFirstName(freelancer.getFirstName());
		freelancerUpdated.setLastName(freelancer.getLastName());
		freelancerUpdated.setEmail(freelancer.getEmail());
		freelancerUpdated.setContactNo(freelancer.getContactNo());
		freelancerUpdated.setDescription(freelancer.getDescription());
		
		freelancerUpdated.setProfilePicture(freelancer.getProfilePicture());
		//Address Updation
		Address address = freelancerUpdated.getPermanentAddress();
		address.setCity(freelancer.getPermanentAddress().getCity());
		address.setCountry(freelancer.getPermanentAddress().getCountry());
		address.setCountry(freelancer.getPermanentAddress().getLandmark());
		address.setCountry(freelancer.getPermanentAddress().getPincode());
		address.setCountry(freelancer.getPermanentAddress().getState());
		
		//Skull Updation
		Skills skill = freelancerUpdated.getSkills();
		skill.setPrimarySkill(freelancer.getSkills().getPrimarySkill());
		skill.setSecondarySkill(freelancer.getSkills().getSecondarySkill());
		skill.setThirdSkill(freelancer.getSkills().getThirdSkill());
		skill.setFourthSkill(freelancer.getSkills().getFourthSkill());
		skill.setFifthSkill(freelancer.getSkills().getFifthSkill());
		return mapper.map(freelancerUpdated,FreelancerDTO.class);
	}


	public GigDTO addNewGig(GigDTO gig) {
			Gigs newGig = mapper.map(gig,Gigs.class);
			newGig.getFreelancer().setId(gig.getFreelancer().getId());	
			return mapper.map(gigDao.save(newGig),GigDTO.class);
	}
	@Override

	public List<ReviewsDTO> getAllReviews(Long freelancerId) {
		  // Assuming you have a method in reviewDao to retrieve reviews by freelancerId
	    List<Reviews> reviews = reviewDao.findByfreelancerId(freelancerId);

	    // Mapping Reviews objects to ReviewsDTO
	    List<ReviewsDTO> reviewsDTOList = reviews.stream()
	            .map(review -> mapper.map(review, ReviewsDTO.class))
	            .collect(Collectors.toList());
		return reviewsDTOList;
	}

	public List<Orders> getOrderDetails(Long freelancerId) {
		List<Orders> finalOrderList = orderDao.findAllOrderByFreelancerId(freelancerId);
		return finalOrderList;
	}


}




