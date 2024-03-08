package com.billmgt.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "bill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO extends BaseDTO{
	
	@Column(name = "catId")
	private Long catId;
	
	@Column(name = "catName")
	private String catName;
		
	@Column(name = "productId")
	private Long productId;
	
	@Column(name = "productName", length = 755)
	private String productName;

	
	@Column(name = "userEmail", length = 755)
	private String userEmail;
	
	@Column(name = "quantity")
	private Long quantity;
	
	@Column(name = "price")
	private Long price;

	@Column(name = "status")
	private String status;

}
