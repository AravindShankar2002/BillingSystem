package com.billmgt.form;

import javax.validation.constraints.NotEmpty;

import com.billmgt.dto.OrderDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderForm  extends BaseForm{
	
	private long productId;
	
	private long userId;

	@NotEmpty(message = "First name is required")
	private String firstName;

	@NotEmpty(message = "Last name is required")
	private String lastName;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Phone number is required")
	private String phoneNumber;
		

	
	public OrderDTO getDTO() {
		OrderDTO bean = new OrderDTO();
		bean.setId(id);
		bean.setProductId(productId);
		bean.setUserId(userId);
	    bean.setFirstName(firstName);
	    bean.setLastName(lastName);
	    bean.setEmail(email);
	    bean.setPhoneNumber(phoneNumber);


		return bean;
	}

	public void populate(OrderDTO bean) {
		id = bean.getId();
        productId = bean.getProductId();
        userId = bean.getUserId();
        firstName = bean.getFirstName();
        lastName = bean.getLastName();
        email  = bean.getEmail();
        phoneNumber = bean.getPhoneNumber();
  

	}
	
}
