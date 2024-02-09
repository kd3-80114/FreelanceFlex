package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Freelancer;

public interface FreelancerDao extends JpaRepository<Freelancer, Long> {

}
