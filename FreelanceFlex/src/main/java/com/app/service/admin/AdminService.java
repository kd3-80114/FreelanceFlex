package com.app.service.admin;

import com.app.dto.admindto.AdminDTO;

public interface AdminService {
	AdminDTO findById(Long id);
}
