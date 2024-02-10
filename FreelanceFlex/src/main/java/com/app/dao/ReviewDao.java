package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Buyer;
import com.app.entities.Reviews;

public interface ReviewDao extends JpaRepository<Buyer, Long>  {

	Reviews save(Reviews map);

}
