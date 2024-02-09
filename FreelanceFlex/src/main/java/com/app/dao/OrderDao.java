package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entities.Orders;

public interface OrderDao extends JpaRepository<Orders, Long> {

}
