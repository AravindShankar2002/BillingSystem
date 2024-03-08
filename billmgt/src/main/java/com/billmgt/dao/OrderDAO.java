package com.billmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billmgt.dto.OrderDTO;



public interface OrderDAO extends JpaRepository<OrderDTO, Long> {

	public OrderDTO findById(long id);
	
	public List<OrderDTO> findByUserId(long id);
}
