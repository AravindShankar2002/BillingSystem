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

import com.billmgt.dao.CategoryDAO;
import com.billmgt.dto.CategoryDTO;
import com.billmgt.dto.ProductDTO;
import com.billmgt.exception.RecordNotFoundException;



@Service
public class CategoryService {

	@Autowired
	public CategoryDAO repository;

	public ResponseEntity<?> Add(CategoryDTO entity) {
		CategoryDTO cEntity = null;
		try {
			CategoryDTO category = repository.findByName(entity.getName());
			if (category == null) {
				cEntity = repository.save(entity);
			} else {
				System.out.println("Duplicate Category..");
				throw new RecordNotFoundException("Category is already available");
			}
		} catch (Exception e) {

			throw new RecordNotFoundException("Category is already available");
		}
		return new ResponseEntity<>(cEntity, HttpStatus.OK);
	}

	public List<CategoryDTO> list() {
		List<CategoryDTO> list = null;

		list = repository.findAll();

		return list;
	}

	public CategoryDTO update(CategoryDTO user) {

		CategoryDTO entity = repository.saveAndFlush(user);
		return entity;
	}

	public List<CategoryDTO> delete(long id) {
		List<CategoryDTO> list = null;
		if (findById(id) != null) {
			repository.deleteById(id);
			list = repository.findAll();
		} else {
			throw new RecordNotFoundException("Not a valid user id");
		}
		return list;
	}

	public CategoryDTO findById(long id) {
		try {
			if (id > 0) {
				CategoryDTO entity = repository.findById(id);
				if (entity == null) {
					throw new RecordNotFoundException("Record not found");

				} else {
					return entity;
				}
			} else {
				throw new RecordNotFoundException("Not a valid user id");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RecordNotFoundException("Record not found");
		}
	}
	
	public Blob getImageById(long id) throws SerialException, SQLException {		
		CategoryDTO img = repository.findById(id);
		byte[] blob = img.getImage();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}

}
