package com.app.service.freelancer;

import com.app.dto.freelancerdto.FreelancerDTO;

public interface FreelanceService {

	FreelancerDTO findById(Long id);
	FreelancerDTO addFreelancer(FreelancerDTO freelancer);
	FreelancerDTO updateFreelancer(Long freelanceId,FreelancerDTO freelancer);

}
