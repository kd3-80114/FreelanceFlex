package com.app.service.admin;

import java.util.List;

import com.app.dto.admindto.AdminDTO;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.app.entities.Orders;

public interface AdminService {
	AdminDTO findById(Long id);


	Buyer findBuyerByEmail(String email);

	Freelancer findFreelancerByEmail(String email);

	String deleteFreelancer(Long freelancerId);

	String deleteBuyer(Long buyerId);

	List<Orders> getFreelancerOrders(Long freelancerId);

	List<Orders> getBuyerOrders(Long buyerId);

	String getFreelancer(Long freelancerId);


	String getBuyer(Long buyerId);

}
