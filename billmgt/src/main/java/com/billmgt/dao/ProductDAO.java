package com.billmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billmgt.dto.ProductDTO;



public interface ProductDAO extends JpaRepository<ProductDTO, Long> {
	
	public ProductDTO findById(long id);
	//public ProductDTO findByCatId(long id);
	public ProductDTO findByName(String pName);
	public List<ProductDTO> findByCatId(long id);

}
