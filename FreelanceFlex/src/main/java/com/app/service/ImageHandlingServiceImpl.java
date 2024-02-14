package com.app.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.app.custom_exceptions.ApiException;
import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.BuyerDao;
import com.app.dao.FreelancerDao;
import com.app.entities.ApiResponse;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;

@Service
@Transactional
public class ImageHandlingServiceImpl implements ImageHandlingService {
//	@Autowired
//	FreelancerDao freelancerDao ;
//	// injecting value of the field read from applicatoin.properties file
//	@Value("${file.upload.location}") // field level DI , <property name n value />
//	// ${file.upload.location} SpEL :Spring expr language
//	private String uploadFolder;
//	
//	@Autowired
//	private BuyerDao buyerDao;
//
//	@Override
//	public ApiResponse uploadImage(Long id, MultipartFile image) throws IOException {
//	    Freelancer freelancer = freelancerDao.findById(id)
//	            .orElseThrow(() -> new ResourceNotFoundException("Freelancer with given id does not exist")); // Exception message modified
//	    freelancer.setProfilePicture(image.getBytes());
//	    freelancerDao.save(freelancer);
//	    return new ApiResponse("Image file uploaded successfully for freelancer id " + id);
//	}
//
//	@Override
//	public byte[] serveImageOfreelancer(long id) {
//		
//		Freelancer freelancer = freelancerDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid emp ID!!!!"));
//
//		if (freelancer != null) {
//		
//			 return freelancer.getProfilePicture();
//		} else
//			throw new ApiException("Image not yet assigned !!!!");
//	}
//	
//	@Override
//	public byte[] serveImageOfbuyer(long id) {
//		
//		Buyer buyer = buyerDao.findById(id).orElseThrow(() -> new ResourceNotFoundException("Invalid emp ID!!!!"));
//
//		if (buyer  != null) {
//		
//			 return buyer .getProfilePicture();
//		} else
//			throw new ApiException("Image not yet assigned !!!!");
//	}

	}


