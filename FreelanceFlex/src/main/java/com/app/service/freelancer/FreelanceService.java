package com.app.service.freelancer;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;


import java.util.List;

import com.app.dto.PaymentDTO;
import com.app.dto.ReviewsDTO;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.ApiResponse;
import com.app.entities.Reviews;

import java.util.List;
import com.app.dto.buyerdto.BuyerDTO;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.Orders;


public interface FreelanceService {

	FreelancerDTO findById(Long id);
	FreelancerDTO addFreelancer(FreelancerDTO freelancer);
	FreelancerDTO updateFreelancer(Long freelanceId,FreelancerDTO freelancer);
	GigDTO addNewGig(GigDTO gig);
	List<ReviewsDTO> getAllReviews(Long freelancerId);
	
	ApiResponse uploadImage(Long id, MultipartFile image)throws IOException;

	public byte[] serveImageOfreelancer(long id);
	List<Orders> getOrderDetails(Long freelancerId);
	List<PaymentDTO> getAllPayments(Long freelancerId);

}
