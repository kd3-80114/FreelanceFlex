package com.app.service.freelancer;
import javax.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.FreelancerDao;
import com.app.dao.GigDao;
import com.app.dto.freelancerdto.FreelancerDTO;
import com.app.dto.freelancerdto.GigDTO;
import com.app.entities.Freelancer;
import com.app.entities.Gigs;
@Service
@Transactional
public class FreelanceServiceImpl implements FreelanceService {

	@Autowired
	private FreelancerDao freelancerDao;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private GigDao gigDao;
	@Override
	public FreelancerDTO findById(Long id) {
		
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
	public GigDTO addNewGig(GigDTO gig) {
			Gigs newGig = mapper.map(gig,Gigs.class);
			newGig.getFreelancer().setId(gig.getFreelancer().getId());	
			return mapper.map(gigDao.save(newGig),GigDTO.class);
	}

}
