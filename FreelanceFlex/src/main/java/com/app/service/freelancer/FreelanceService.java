package com.app.service.freelancer;

import java.util.List;

import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.Orders;

public interface FreelanceService {

	FreelancerDTO findById(Long id);
	FreelancerDTO addFreelancer(FreelancerDTO freelancer);
	FreelancerDTO updateFreelancer(Long freelanceId,FreelancerDTO freelancer);
	GigDTO addNewGig(GigDTO gig);
	List<Orders> getOrderDetails(Long freelancerId);
}
