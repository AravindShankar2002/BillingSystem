package com.billmgt.form;

import javax.validation.constraints.NotEmpty;

import com.billmgt.dto.BillDTO;
import com.billmgt.dto.CartDTO;
import com.billmgt.utility.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartForm extends BaseForm {

	@NotEmpty(message = "Quantity is required")
	private String quantity;
	
	
	private long productId;
	
	public CartDTO getDTO() {
		CartDTO bean = new CartDTO();
		
		bean.setId(id);	 
		bean.setProductId(productId);
		bean.setQuantity(DataUtility.getLong(quantity));
	
		return bean;
	}

	public void populate(CartDTO bean) {
		id = bean.getId();
		productId = bean.getProductId();
		quantity = DataUtility.getStringData(bean.getQuantity());
		
	}
	
}
