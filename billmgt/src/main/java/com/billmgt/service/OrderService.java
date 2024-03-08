package com.billmgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.billmgt.dao.OrderDAO;
import com.billmgt.dto.OrderDTO;
import com.billmgt.exception.RecordNotFoundException;

@Service
public class OrderService {

	@Autowired
	public OrderDAO repository;

	public ResponseEntity<?> Add(OrderDTO entity) {
		OrderDTO cEntity = null;
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

	public List<OrderDTO> list() {
		List<OrderDTO> list = null;

		list = repository.findAll();

		return list;
	}
	
	public List<OrderDTO> list(long userId) {
		List<OrderDTO> list = null;
		list = repository.findByUserId(userId);
		return list;
	}

	public OrderDTO update(OrderDTO dto) {

		OrderDTO entity = repository.saveAndFlush(dto);
		return entity;
	}

	public List<OrderDTO> delete(long id) {
		List<OrderDTO> list = null;
		if (findById(id) != null) {
			repository.deleteById(id);
			list = repository.findAll();
		} else {
			throw new RecordNotFoundException("Not a valid record id");
		}
		return list;
	}

	public OrderDTO findById(long id) {
		try {
			if (id > 0) {
				OrderDTO entity = repository.findById(id);
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
