package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Gigs;

public interface GigDao extends JpaRepository<Gigs, Long> {
public List<Gigs> findByFreelancerId(Long id);
}
