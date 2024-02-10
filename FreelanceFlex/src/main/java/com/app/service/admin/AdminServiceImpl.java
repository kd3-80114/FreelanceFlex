package com.app.service.admin;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;

import com.app.dao.BuyerDao;
import com.app.dao.FreelancerDao;

import com.app.dao.AdminDao;

import com.app.dto.admindto.AdminDTO;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private AdminDao admindao;
	@Autowired
	private BuyerDao buyerdao;
	@Autowired
	private FreelancerDao freelancerdao;
	
	@Override
	public AdminDTO findById(Long id) {	
	return mapper.map(admindao.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException
				("Admin with given id does not exist")),
				AdminDTO.class) ;	

		}

	
	
	@Override
	public Buyer findBuyerByEmail(String email) {
		Buyer buyer=buyerdao.findByEmail(email);
		buyer.getBuyerReview().size();
		buyer.getBuyerPayment().size();
		buyer.getBuyerOrders().size();
		buyer.getBuyerIssues().size();
		return buyer;
	}
	
	@Override
	public Freelancer findFreelancerByEmail(String email) {
		Freelancer freelancer=freelancerdao.findByEmail(email);
		freelancer.getGigs().size();
		freelancer.getFreelancerReview().size();
		freelancer.getFreelancerPayment().size();
		freelancer.getFreelanceIssues().size();
		freelancer.getFreelancerOrders().size();
		return freelancer;
	}
	
	@Override
	public String deleteFreelancer(Long freelancerId) {
		freelancerdao.deleteById(freelancerId);
		return null;
	}

}
