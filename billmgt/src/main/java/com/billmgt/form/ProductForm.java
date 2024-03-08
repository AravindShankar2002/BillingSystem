package com.billmgt.form;

import com.billmgt.dto.ProductDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductForm extends BaseForm {

	private String name;

	private Long catId;

	private Long quantity;

	private Long price;
	
	

	public ProductDTO getDTO() {
		ProductDTO bean = new ProductDTO();
		bean.setId(id);
		bean.setCatId(catId);
		bean.setName(name);
		bean.setQuantity(quantity);
		bean.setPrice(price);

		return bean;
	}

	public void populate(ProductDTO bean) {
		id = bean.getId();
		catId = bean.getCatId();
		name = bean.getName();
		quantity = bean.getQuantity();
		price = bean.getPrice();

	}

}
