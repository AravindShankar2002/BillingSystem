package com.billmgt.ctl;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.billmgt.dto.CategoryDTO;
import com.billmgt.dto.ProductDTO;
import com.billmgt.exception.RecordNotFoundException;
import com.billmgt.form.ProductForm;
import com.billmgt.service.CategoryService;
import com.billmgt.service.ProductService;

@Controller
public class ProdutCtl {

	@Autowired
	public ProductService service;
	
	@Autowired
	public CategoryService categoryService;
	
	@GetMapping("/product")
	public String product(@ModelAttribute("form")ProductForm form, Model model) {
		
		 List<CategoryDTO> list =  categoryService.list();
		 model.addAttribute("categoryList", list);
		return "product";
	}

	@PostMapping("/addProduct")
	public String Add(@RequestParam(value = "image") MultipartFile image, @Valid @ModelAttribute("form")ProductForm form,  BindingResult bindingResult, Model model) throws IOException {

		System.out.println("form: "+form);
		try {
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult : "+bindingResult);
			return "product";
		}else {
			ProductDTO bean = form.getDTO();
			long size = image.getSize();
			
			System.out.println("Image Size: "+size);
			
			bean.setImage(image.getBytes());
			if(form.getId()>0) {
				service.update(bean);
				model.addAttribute("success", "product Updated successfully");
			}else {
			service.Add(bean);
			model.addAttribute("success", "product Added successfully");
			}
			
			return "product";
		}}catch (RecordNotFoundException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
			return "product";
		}
	}
	
	@GetMapping("/productList")
	public String list(@ModelAttribute("form")ProductForm form, Model model){
	List<ProductDTO> list = service.list();
	model.addAttribute("list", list);
	
	
	
	return "productlist";
		
	}
	
	@GetMapping("/bycategory")
	public String bycategory(@ModelAttribute("form")ProductForm form, Model model, @RequestParam("id") long id){
	List<ProductDTO> list = service.productByCategory(id);
	model.addAttribute("list", list);
	return "productlist";
		
	}
	
	
	@GetMapping("/productEdit")	
	public String update(@ModelAttribute("form")ProductForm form, Model model, @RequestParam("id") long id ){
		ProductDTO bean = service.findById(id);
		form.populate(bean);
		 List<CategoryDTO> list =  categoryService.list();
		 model.addAttribute("categoryList", list);
		model.addAttribute("bean",bean);	
		return "product";
	}
	
	@GetMapping("/productDelete")	
	public String delete(@ModelAttribute("form")ProductForm form, Model model, @RequestParam("id") long id ) throws Exception{
		service.delete(id);	
		
		List<ProductDTO> list =	service.list();
		model.addAttribute("list", list);
		model.addAttribute("success", "product Deleted successfully");
		return "productlist";
	}

	@GetMapping("/getProductImage/{id}")
	public void getNewsImage(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
		response.setContentType("image/jpeg");		
		Blob blb=service.getImageById(id);				
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	
	}
	

	
}
