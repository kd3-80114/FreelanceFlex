package com.app.service.admin;

import com.app.dto.admindto.AdminDTO;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;

public interface AdminService {
	AdminDTO findById(Long id);

	Buyer findBuyerByEmail(String email);

	Freelancer findFreelancerByEmail(String email);

	String deleteFreelancer(Long freelancerId);
}
