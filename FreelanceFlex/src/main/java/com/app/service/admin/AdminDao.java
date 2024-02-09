package com.app.service.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Admin;

public interface AdminDao extends JpaRepository<Admin, Long> {

}
