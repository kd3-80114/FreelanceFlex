package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Buyer;
import com.app.entities.Freelancer;
import com.app.entities.Reviews;

public interface ReviewDao extends JpaRepository<Reviews, Long>  {

	//Reviews save(Reviews map);
	 @Query("SELECT r FROM Reviews r WHERE r.buyer.id = :buyerId")
	    List<Reviews> findByBuyerId(Long buyerId);
	 
	 @Query("SELECT r FROM Reviews r WHERE r.freelancer.id = :freelancerId")
	List<Reviews> findByfreelancerId(Long freelancerId);
	 
	 @Query("SELECT r.freelancer.id FROM Reviews r WHERE r.buyer.id = :buyerId")
	 List<Long> findFreelancerIdsByBuyerId( @Param("buyerId")Long buyerId);
//	 @Query("SELECT DISTINCT r.freelancer.id FROM Reviews r WHERE r.buyer.id = :buyerId")
//	    List<Long> findFreelancerIdsByBuyerId(@Param("buyerId") Long buyerId);
}
