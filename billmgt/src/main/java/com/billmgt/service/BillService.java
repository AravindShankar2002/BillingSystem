package com.billmgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.billmgt.dao.BillDAO;
import com.billmgt.dao.OrderDAO;
import com.billmgt.dto.BillDTO;
import com.billmgt.dto.OrderDTO;
import com.billmgt.exception.RecordNotFoundException;

@Service
public class BillService {

	@Autowired
	public BillDAO repository;

	public ResponseEntity<?> Add(BillDTO entity) {
		BillDTO cEntity = null;
		try {
			
			if (entity != null) {
				cEntity = repository.save(entity);
			} else {
				
				throw new RecordNotFoundException("Some Thing Went Wrong..");
			}
		} catch (Exception e) {

			throw new RecordNotFoundException("Something went wrong.");
		}
		return new ResponseEntity<>(cEntity, HttpStatus.OK);
	}

	public List<BillDTO> list() {
		List<BillDTO> list = null;

		list = repository.findAll();

		return list;
	}
	
	public List<BillDTO> list(String userEmail) {
		List<BillDTO> list = null;
		list = repository.findByUserEmail(userEmail);
		return list;
	}

	public BillDTO update(BillDTO dto) {

		BillDTO entity = repository.saveAndFlush(dto);
		return entity;
	}

	public List<BillDTO> delete(long id) {
		List<BillDTO> list = null;
		if (findById(id) != null) {
			repository.deleteById(id);
			list = repository.findAll();
		} else {
			throw new RecordNotFoundException("Not a valid record id");
		}
		return list;
	}

	public BillDTO findById(long id) {
		try {
			if (id > 0) {
				BillDTO entity = repository.findById(id);
				if (entity == null) {
					throw new RecordNotFoundException("Record not found");

				} else {
					return entity;
				}
			} else {
				throw new RecordNotFoundException("Not a valid record id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RecordNotFoundException("Record not found");
		}
	}
}
