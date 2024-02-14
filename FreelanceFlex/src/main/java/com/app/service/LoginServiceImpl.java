package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.LoginDao;
import com.app.dto.LoginCredDTO;
import com.app.dto.LoginResponseDTO;
import com.app.entities.SignIn;
@Service
@Transactional 
public class LoginServiceImpl implements LoginService{
	@Autowired
	private LoginDao loginDao;
	@Autowired
	private ModelMapper mapper;

	public  LoginResponseDTO verifyUser(LoginCredDTO userToVerify) {
		
		SignIn user=loginDao.findByEmailAndPassword(userToVerify.getEmail(),userToVerify.getPassword());
		
		//System.out.println(user.getUserRole());
		if(user!=null)
		{
			LoginResponseDTO response=mapper.map(user,LoginResponseDTO.class);
			response.setRole(user.getUserRole());
			return response;			
		}
		return null;
	}
}
