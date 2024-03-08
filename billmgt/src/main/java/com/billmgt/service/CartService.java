package com.billmgt.service;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.billmgt.dao.CartDAO;
import com.billmgt.dto.CartDTO;
import com.billmgt.dto.ProductDTO;
import com.billmgt.exception.RecordNotFoundException;

@Service
public class CartService {

	@Autowired
	public CartDAO repository;
	
	public ResponseEntity<?> Add(CartDTO entity) {
		CartDTO cEntity = null;
		try {
			   
			cEntity = repository.findByproductId(entity.getProductId());
			
			if (cEntity == null) {
				cEntity = repository.save(entity);
			} else {
				
				throw new RecordNotFoundException("Product is already in the cart");
			}
		} catch (Exception e) {

			throw new RecordNotFoundException("Something went wrong.");
		}
		return new ResponseEntity<>(cEntity, HttpStatus.OK);
	}
	

	public List<CartDTO> list() {
		List<CartDTO> list = null;

		list = repository.findAll();

		return list;
	}
	
	public List<CartDTO> list(long userId) {
		List<CartDTO> list = null;
		list = repository.findByUserId(userId);
		return list;
	}

	public CartDTO update(CartDTO dto) {

		CartDTO entity = repository.saveAndFlush(dto);
		return entity;
	}

	public List<CartDTO> delete(long id) {
		List<CartDTO> list = null;
		if (findById(id) != null) {
			repository.deleteById(id);
			list = repository.findAll();
		} else {
			throw new RecordNotFoundException("Not a valid record id");
		}
		return list;
	}
	
	public void deleteCart() {
	     repository.deleteAll();
	}

	public CartDTO findById(long id) {
		try {
			if (id > 0) {
				CartDTO entity = repository.findById(id);
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
	
	public Blob getImageById(long id) throws SerialException, SQLException {		
		CartDTO img = repository.findById(id);
		byte[] blob = img.getImage();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}
	
}
