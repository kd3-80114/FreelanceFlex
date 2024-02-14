package com.app.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Orders;

public interface OrderDao extends JpaRepository<Orders, Long> {
	
		@Query("SELECT o FROM Orders o WHERE o.buyer.id = :buyerId ORDER BY o.startDate DESC")
	    List<Orders> findAllOrderByBuyerId(@Param("buyerId") Long buyerId);

		@Query("SELECT o FROM Orders o WHERE o.freelancer.id = :freelancerId ORDER BY o.startDate DESC")
		List<Orders> findAllOrderByFreelancerId(@Param("freelancerId") Long freelancerId);
}
