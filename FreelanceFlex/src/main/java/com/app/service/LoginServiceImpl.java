package com.app.service;
import com.app.dao.*;
import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AdminDao;
import com.app.dao.FreelancerDao;
import com.app.dao.LoginDao;
import com.app.dto.LoginCredDTO;
import com.app.dto.LoginResponseDTO;
import com.app.entities.Admin;
import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.app.entities.SignIn;
import com.app.entities.User;
@Service
@Transactional 
public class LoginServiceImpl implements LoginService{
	@Autowired
	private SignInDao loginDao;
	@Autowired
	private FreelancerDao freelancerDao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private BuyerDao buyerDao;
	@Autowired
	private ModelMapper mapper;

	public User verifyUser(LoginCredDTO userToVerify) {
		
		SignIn user=loginDao.findByEmail(userToVerify.getEmail());
		
		
		//System.out.println(user.getUserRole());
//		if(user!=null)
//		{
//			LoginResponseDTO response=mapper.map(user,LoginResponseDTO.class);
//			response.setUserRole(user.getUserRole());
//			return response;			
//		}
		System.out.println(user);
		if(user.getUserRole().toString().compareTo("ROLE_FREELANCER")==0)
		{
			Freelancer freelancer=freelancerDao.findByEmail(user.getEmail());
			return freelancer;
		}
		else if(user.getUserRole().toString().compareTo("ROLE_BUYER")==0)
		{
			Buyer buyer=buyerDao.findByEmail(user.getEmail());
			return buyer;
		}
		else if(user.getUserRole().toString().compareTo("ROLE_ADMIN")==0)
		{
			Admin admin=adminDao.findByEmail(user.getEmail());
			return admin;
		}
		return null;
		
	}
}
