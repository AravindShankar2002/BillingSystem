package com.billmgt.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.billmgt.dto.BillDTO;
import com.billmgt.dto.CategoryDTO;
import com.billmgt.dto.ProductDTO;
import com.billmgt.dto.UserDTO;
import com.billmgt.exception.RecordNotFoundException;
import com.billmgt.form.BillForm;
import com.billmgt.service.BillService;
import com.billmgt.service.CategoryService;
import com.billmgt.service.ProductService;
import com.billmgt.service.UserService;


@Controller
public class BillCtl {
	
	@Autowired
	public BillService service;

	@Autowired
	public ProductService productService;
	
	@Autowired
	public CategoryService categoryService;

	@Autowired
	public UserService userService;


	@GetMapping("/bill")
	public String order(@ModelAttribute("form") BillForm form, Model model, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		List<ProductDTO> list = productService.list();
		model.addAttribute("productList", list);
		
		List<UserDTO> userlist = userService.list();
		model.addAttribute("userlist", userlist);
		
		model.addAttribute("user", user);
		return "bill";
	}

	@PostMapping("/addBill")
	public String Add(@Valid @ModelAttribute("form") BillForm form, BindingResult bindingResult, Model model)
			throws IOException {
		try {
			if (bindingResult.hasErrors()) {
				System.out.println("bindingResult : " + bindingResult);
				return "bill";
			} else {
				BillDTO bean = form.getDTO();
				ProductDTO pDto = productService.findById(bean.getProductId());
				bean.setProductName(pDto.getName());
				CategoryDTO cDto = categoryService.findById(pDto.getCatId());
				bean.setCatId(pDto.getCatId());
                bean.setCatName(cDto.getName());
                bean.setStatus("Pendding");
					if (form.getId() > 0) {
						service.update(bean);
						model.addAttribute("success", "Bill Updated successfully");
					} else {
						service.Add(bean);
						model.addAttribute("success", "Bill Added successfully");
					}
				}
			
		} catch (RecordNotFoundException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
			return "bill";
		}
		return "bill";
	}

	@GetMapping("/billlist")
	public String list(@ModelAttribute("form") BillForm form, Model model, HttpSession session) {
		List<BillDTO> list = null;
		UserDTO uDto = (UserDTO)session.getAttribute("user");
		if(uDto==null) {
			return "redirect:/home";
		}else {

		if(uDto.getUserRole().equals("Admin")) {
			 list = service.list();
		}else {
			list = service.list(uDto.getEmail());
		}
		
		}
		
		model.addAttribute("list", list);
		return "billlist";

	}
	

	

	@GetMapping("/billEdit")
	public String update(@ModelAttribute("form")BillForm form, Model model, @RequestParam("id") long id) {
		BillDTO bean = service.findById(id);
		form.populate(bean);
		List<ProductDTO> list = productService.list();
		model.addAttribute("productList", list);
		model.addAttribute("bean", bean);
		return "bill";
	}

	@GetMapping("/billDelete")
	public String delete(@ModelAttribute("form")BillForm form, Model model, @RequestParam("id") long id)
			throws Exception {
		service.delete(id);

		List<BillDTO> list = service.list();
		model.addAttribute("list", list);
		model.addAttribute("success", "Bill Deleted successfully");
		return "billlist";
	}
	


}
