package com.billmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.billmgt.dto.PaymentDTO;
import com.billmgt.dto.UserDTO;

public interface PaymentDAO extends JpaRepository<PaymentDTO, Long> {
	
	public PaymentDTO findById(long id);
	public List<PaymentDTO> findByEmail(String email);

}
