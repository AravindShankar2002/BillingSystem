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

import com.billmgt.dao.ProductDAO;
import com.billmgt.dto.ProductDTO;
import com.billmgt.exception.RecordNotFoundException;


@Service
public class ProductService {

	@Autowired
	public ProductDAO repository;

	public ResponseEntity<?> Add(ProductDTO entity) {
		ProductDTO cEntity = null;
		try {
			ProductDTO product = repository.findByName(entity.getName());
			if (product == null) {
				cEntity = repository.save(entity);
			} else {
				System.out.println("Duplicate product..");
				throw new RecordNotFoundException("product is already available");
			}
		} catch (Exception e) {

			throw new RecordNotFoundException("Something went wrong.");
		}
		return new ResponseEntity<>(cEntity, HttpStatus.OK);
	}

	public List<ProductDTO> list() {
		List<ProductDTO> list = null;

		list = repository.findAll();

		return list;
	}
	
	public List<ProductDTO> productByCategory(long catId) {
		List<ProductDTO> list = null;

		list = repository.findByCatId(catId);

		return list;
	}


	public ProductDTO update(ProductDTO dto) {

		ProductDTO entity = repository.saveAndFlush(dto);
		return entity;
	}

	public List<ProductDTO> delete(long id) {
		List<ProductDTO> list = null;
		if (findById(id) != null) {
			repository.deleteById(id);
			list = repository.findAll();
		} else {
			throw new RecordNotFoundException("Not a valid record id");
		}
		return list;
	}

	public ProductDTO findById(long id) {
		try {
			if (id > 0) {
				ProductDTO entity = repository.findById(id);
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
		ProductDTO img = repository.findById(id);
		byte[] blob = img.getImage();
		Blob bBlob = new SerialBlob(blob);
		return bBlob;
	}
}
