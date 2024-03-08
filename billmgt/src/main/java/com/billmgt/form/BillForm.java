package com.billmgt.form;

import javax.validation.constraints.NotEmpty;

import com.billmgt.dto.BillDTO;
import com.billmgt.dto.ProductDTO;
import com.billmgt.utility.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BillForm extends BaseForm {
	
	
	private String productId;

	@NotEmpty(message = "Quantity is required")
	private String quantity;

	@NotEmpty(message = "Price is required")
	private String price;
	
	@NotEmpty(message = "Select User is required")
	private String userEmail;
	
	
	
	public BillDTO getDTO() {
		BillDTO bean = new BillDTO();
		bean.setId(id);
	    bean.setProductId(DataUtility.getLong(productId));
		bean.setQuantity(DataUtility.getLong(quantity));
		bean.setPrice(DataUtility.getLong(price));
		bean.setUserEmail(userEmail);
		

		return bean;
	}

	public void populate(BillDTO bean) {
		id = bean.getId();
		productId = DataUtility.getStringData(bean.getProductId());
		quantity = DataUtility.getStringData(bean.getQuantity());
		price = DataUtility.getStringData(bean.getPrice());
		userEmail = bean.getUserEmail();

	}

}
