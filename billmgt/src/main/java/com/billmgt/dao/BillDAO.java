package com.billmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billmgt.dto.BillDTO;


public interface BillDAO extends JpaRepository<BillDTO, Long>{

	public BillDTO findById(long id);
	
	public List<BillDTO> findByUserEmail(String email);
}
