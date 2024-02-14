package com.app.service.freelancer;
import java.util.List;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.Gigs;
import com.app.entities.Orders;

public interface FreelanceService {

	FreelancerDTO findById(Long id);
	FreelancerDTO addFreelancer(FreelancerDTO freelancer);
	FreelancerDTO updateFreelancer(Long freelanceId,FreelancerDTO freelancer);
	GigDTO addNewGig(GigDTO gig);
	List<Orders> getOrderDetails(Long freelancerId);
	List<Gigs> getAllGigs(Long freelancerId);
}
