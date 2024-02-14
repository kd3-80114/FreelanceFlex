package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entities.Payment;


public interface PaymentDao extends JpaRepository<Payment, Long>{
	//Payments save
	@Query("SELECT r FROM Payment r WHERE r.buyer.id = :buyerId")
    List<Payment> findByBuyerId(Long buyerId);
	@Query("SELECT r FROM Payment r WHERE r.freelancer.id = :freelancerId")
	List<Payment> findByfreelancerId(Long freelancerId);
	
}
