package com.app.service.admin;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.custom_exceptions.ResourceNotFoundException;
import com.app.dao.AdminDao;
import com.app.dto.admindto.AdminDTO;

@Service
@Transactional
public class AdminServiceImpl implements AdminService{
	
	
	@Autowired
	private AdminDao admindao;
	@Autowired
	private ModelMapper mapper;
	@Override
	public AdminDTO findById(Long id) {
		
		return mapper.map(admindao.findById(id)
				.orElseThrow(()->
				new ResourceNotFoundException
				("Admin with given id does not exist")),
				AdminDTO.class) ;	
		}
}
