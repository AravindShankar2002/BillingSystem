package com.billmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billmgt.dto.CartDTO;

public interface CartDAO extends JpaRepository<CartDTO, Long> {
	

	public CartDTO findById(long id);
	
	public List<CartDTO> findByUserId(long id);
	
	public CartDTO findByproductId(long productId);

}
