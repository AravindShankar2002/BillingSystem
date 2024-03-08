package com.billmgt.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO extends BaseDTO{
	
	
	
	@Column(name = "catId")
	private Long catId;
	
	@Column(name = "name", length = 755)
	private String name;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "price")
	private Long price;

	 @Column(name = "image", columnDefinition = "LONGBLOB")
	 private byte[] image;

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
