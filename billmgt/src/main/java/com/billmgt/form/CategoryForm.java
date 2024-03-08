package com.billmgt.form;
import com.billmgt.dto.CategoryDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryForm extends BaseForm {
	
	private String name;
	
	
	  public CategoryDTO getDTO() {			
		  CategoryDTO bean=new CategoryDTO();			
			bean.setId(id);
	        bean.setName(name);
	

			return bean;
		}

		public void populate(CategoryDTO bean) {
			id = bean.getId();
			name = bean.getName();
	
		}



}
