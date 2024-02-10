package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Buyer;

public interface BuyerDao extends JpaRepository<Buyer, Long> {


	
}
