package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Freelancer;
import com.app.entities.Gigs;

public interface GigDao extends JpaRepository<Gigs, Long> {
public List<Gigs> findByFreelancerId(Long id);

@Query("SELECT g.freelancer.id FROM Gigs g WHERE g.id = :gigsId")
public Long findFreelancerById(Long gigsId);


}
