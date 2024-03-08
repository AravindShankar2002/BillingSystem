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
import org.springframework.web.multipart.MultipartFile;

import com.billmgt.dto.CartDTO;
import com.billmgt.dto.CategoryDTO;
import com.billmgt.dto.OrderDTO;
import com.billmgt.dto.ProductDTO;
import com.billmgt.dto.UserDTO;
import com.billmgt.exception.RecordNotFoundException;
import com.billmgt.form.OrderForm;
import com.billmgt.form.ProductForm;
import com.billmgt.service.CartService;
import com.billmgt.service.OrderService;
import com.billmgt.service.ProductService;
import com.billmgt.utility.DataUtility;

@Controller
public class OrderCtl {

	@Autowired
	public OrderService service;

	@Autowired
	public ProductService productService;
	
	@Autowired
	public CartService cartService;

	@GetMapping("/order")
	public String order(@ModelAttribute("form") OrderForm form, Model model, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		List<ProductDTO> list = productService.list();
		model.addAttribute("productList", list);
		model.addAttribute("user", user);
		return "order";
	}
	
	@GetMapping("/orderByCart")
	public String orderByCart(@ModelAttribute("form") OrderForm form, Model model, HttpSession session) {
		
		UserDTO user = (UserDTO) session.getAttribute("user");
		model.addAttribute("user", user);	
		return "ordercartview";
	}
	
	
	@PostMapping("/addCartOrder")
	public String addCartOrder(@Valid @ModelAttribute("form") OrderForm form, BindingResult bindingResult, Model model, HttpSession session)
			throws IOException {

		System.out.println("form: " + form);
		UserDTO user = (UserDTO) session.getAttribute("user");
		try {
			if (bindingResult.hasErrors()) {
				System.out.println("bindingResult : " + bindingResult);
				return "ordercartview";
			} else {
				
				List<CartDTO> cartList = cartService.list(user.getId());
				long totalCartPrice = 0;
				for (CartDTO cartDTO : cartList) {
				     	OrderDTO bean = form.getDTO();
					    bean.setNumberOfUnit(DataUtility.getStringData(cartDTO.getQuantity()));
					    bean.setProductId(cartDTO.getProductId());
					    bean.setProductName(cartDTO.getProductName());
					    bean.setStatus("Booked");
					    bean.setTotalPrice(cartDTO.getPrice());
					    bean.setUserId(user.getId());
		                bean.setOrderDate(DataUtility.getCurrentDate());
		                service.Add(bean);
		                
		                totalCartPrice = totalCartPrice + cartDTO.getPrice();
				}

				model.addAttribute("success", "Order Added successfully");
									

				return "redirect:/cartpayment?totalPrice="+totalCartPrice;
			}
		} catch (RecordNotFoundException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
			return "ordercartview";
		}
	}
	

	@PostMapping("/addOrder")
	public String Add(@Valid @ModelAttribute("form") OrderForm form, BindingResult bindingResult, Model model)
			throws IOException {

		System.out.println("form: " + form);
		try {
			if (bindingResult.hasErrors()) {
				System.out.println("bindingResult : " + bindingResult);
				return "order";
			} else {
				OrderDTO bean = form.getDTO();
				ProductDTO pDto = productService.findById(bean.getProductId());
				bean.setProductName(pDto.getName());
				long numberOfUnit = DataUtility.getLong(bean.getNumberOfUnit());
				
				long totalPrice = numberOfUnit * pDto.getPrice();
                model.addAttribute("totalPrice", totalPrice);
                bean.setTotalPrice(totalPrice);
                bean.setStatus("Booked");
                bean.setOrderDate(DataUtility.getCurrentDate());
				if (pDto.getQuantity() >= numberOfUnit && numberOfUnit>0 ) {

					pDto.setQuantity(pDto.getQuantity() - numberOfUnit);
					productService.update(pDto);
					if (form.getId() > 0) {
						service.update(bean);
						model.addAttribute("success", "Order Updated successfully");
					} else {
						service.Add(bean);
						model.addAttribute("success", "Order Added successfully");
					}
				}else {
					model.addAttribute("error", "Number of unit should be less then available Quantity.");
					return "order";
				}

				return "redirect:/payment?billId=0";
			}
		} catch (RecordNotFoundException e) {
			// TODO: handle exception
			model.addAttribute("error", e.getMessage());
			e.printStackTrace();
			return "order";
		}
	}

	@GetMapping("/orderList")
	public String list(@ModelAttribute("form") ProductForm form, Model model, HttpSession session) {
		List<OrderDTO> list = null;
		UserDTO uDto = (UserDTO)session.getAttribute("user");
		if(uDto==null) {
			return "redirect:/home";
		}else {

		if(uDto.getUserRole().equals("Admin")) {
			 list = service.list();
		}else {
			list = service.list(uDto.getId());
		}
		
		}
		
		model.addAttribute("list", list);
		return "orderlist";

	}
	
	@GetMapping("/saleslist")
	public String saleslist(@ModelAttribute("form") ProductForm form, Model model, HttpSession session) {
		List<OrderDTO> list = null;
		UserDTO uDto = (UserDTO)session.getAttribute("user");
		if(uDto==null) {
			return "redirect:/home";
		}else {

		if(uDto.getUserRole().equals("Admin")) {
			 list = service.list();
		}else {
			list = service.list(uDto.getId());
		}
		
		}
		
		model.addAttribute("list", list);
		return "saleslist";

	}
	

	@GetMapping("/orderEdit")
	public String update(@ModelAttribute("form") OrderForm form, Model model, @RequestParam("id") long id) {
		OrderDTO bean = service.findById(id);
		form.populate(bean);
		List<ProductDTO> list = productService.list();
		model.addAttribute("productList", list);
		;
		model.addAttribute("bean", bean);
		return "order";
	}

	@GetMapping("/orderDelete")
	public String delete(@ModelAttribute("form") OrderForm form, Model model, @RequestParam("id") long id)
			throws Exception {
		service.delete(id);

		List<OrderDTO> list = service.list();
		model.addAttribute("list", list);
		model.addAttribute("success", "Order Deleted successfully");
		return "orderlist";
	}
	
	@GetMapping("/cancel")
	public String cancel(@ModelAttribute("form") OrderForm form, Model model, @RequestParam("id") long id)
			throws Exception {
		
		OrderDTO oDto = service.findById(id);
		oDto.setStatus("Canceled");
		service.update(oDto);

		List<OrderDTO> list = service.list();
		model.addAttribute("list", list);
		model.addAttribute("success", "Record Updated successfully");
		return "orderlist";
	}

}
