package com.billmgt.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.Base64;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.billmgt.dto.CategoryDTO;
import com.billmgt.dto.UserDTO;
import com.billmgt.exception.RecordNotFoundException;
import com.billmgt.form.CategoryForm;
import com.billmgt.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class CategoryCtl {
	
	@Autowired
	public CategoryService service;
	
	@GetMapping("/category")
	public String category(@ModelAttribute("form")CategoryForm form, Model model, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if(user == null) {
			return "login";
		}else {
			return "category";	
		}
		
	}

	@PostMapping("/addCategory")
	public String Add(@RequestParam(value = "image") MultipartFile image, @Valid @ModelAttribute("form")CategoryForm form,  BindingResult bindingResult, Model model) throws IOException {

		System.out.println("form: "+form);
		try {
		if (bindingResult.hasErrors()) {
			System.out.println("bindingResult : "+bindingResult);
			return "category";
		}else {
			CategoryDTO bean = form.getDTO();			
			bean.setImage(image.getBytes());
			if(form.getId()>0) {
				service.update(bean);
				model.addAttribute("success", "Category Updated successfully");
			}else {
			service.Add(bean);
			model.addAttribute("success", "Category Added successfully");
			}
			
			return "category";
		}}catch (RecordNotFoundException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
			return "category";
		}
	}
	
	@GetMapping("/categoryList")
	public String list(@ModelAttribute("form")CategoryForm form, Model model){
	List<CategoryDTO> list = service.list();
	model.addAttribute("list", list);
	return "categorylist";
		
	}
	
	
	@GetMapping("/categoryEdit")	
	public String update(@ModelAttribute("form")CategoryForm form, Model model, @RequestParam("id") long id ){
		CategoryDTO bean = service.findById(id);
		form.populate(bean);
		model.addAttribute("bean",bean);	
		return "category";
	}
	
	@GetMapping("/categoryDelete")	
	public String delete(@ModelAttribute("form")CategoryForm form, Model model, @RequestParam("id") long id ) throws Exception{
		service.delete(id);	
		
		List<CategoryDTO> list =	service.list();
		model.addAttribute("list", list);
		model.addAttribute("success", "Category Deleted successfully");
		return "categorylist";
	}

	@GetMapping("/getCategoryImage/{id}")
	public void getNewsImage(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
		response.setContentType("image/jpeg");		
		Blob blb=service.getImageById(id);				
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());
	
	}
	

}
