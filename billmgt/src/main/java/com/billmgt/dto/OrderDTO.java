package com.billmgt.dto;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="OrderDTO")
@Getter
@Setter
public class OrderDTO extends BaseDTO{

	@Column(name = "productId")
	private long productId;

	@Column(name = "userId")
	private long userId;
	
	@Column(name = "productName", length = 755)
	private String productName;
	
	@Column(name = "firstName", length = 755)
	private String firstName;

	@Column(name = "lastName", length = 755)
	private String lastName;
	
	@Column(name = "email", length = 755)
	private String email;
	
	@Column(name = "phoneNumber", length = 755)
	private String phoneNumber;
	
	@Column(name = "numberOfUnit", length = 755)
	private String numberOfUnit;
	
	@Column(name = "totalPrice")
	private long totalPrice;
	
	@Column(name = "status", length = 755)
	private String status;
	
	@Column(name = "orderDate")
	private LocalDate orderDate;
	
}
