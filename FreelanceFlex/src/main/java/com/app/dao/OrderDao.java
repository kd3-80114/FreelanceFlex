package com.app.dao;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entities.Freelancer;
import com.app.entities.Orders;

public interface OrderDao extends JpaRepository<Orders, Long> {
	
		@Query("SELECT o FROM Orders o WHERE o.buyer.id = :buyerId ORDER BY o.startDate DESC")
	    List<Orders> findAllOrderByBuyerId(@Param("buyerId") Long buyerId);

		@Query("SELECT o FROM Orders o WHERE o.freelancer.id = :freelancerId ORDER BY o.startDate DESC")
		List<Orders> findAllOrderByFreelancerId(@Param("freelancerId") Long freelancerId);

	
//		 Long findFreelancerByOrderId(@Param("orderId") Long orderId);
		@Query("SELECT o.freelancer FROM Orders o WHERE o.id = :orderId")
	    Optional<Freelancer> findFreelancerByOrderId(@Param("orderId") Long orderId);

}
