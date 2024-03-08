package com.billmgt.ctl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
import com.billmgt.dto.CartDTO;
import com.billmgt.dto.ProductDTO;
import com.billmgt.dto.UserDTO;
import com.billmgt.exception.RecordNotFoundException;
import com.billmgt.form.CartForm;
import com.billmgt.service.CartService;
import com.billmgt.service.CategoryService;
import com.billmgt.service.ProductService;
import com.billmgt.service.UserService;
import com.billmgt.utility.DataUtility;

@Controller
public class CartCtl {

	@Autowired
	public CartService service;

	@Autowired
	public ProductService productService;

	@Autowired
	public CategoryService categoryService;

	@Autowired
	public UserService userService;

	@GetMapping("/cartList")
	public String cart(@ModelAttribute("form") CartForm form, Model model, HttpSession session) {
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			return "login";
		} else {
			List<CartDTO> list = service.list(user.getId());
			long totalPrice = 0;

			for (CartDTO cartDTO : list) {
				totalPrice = totalPrice + cartDTO.getPrice();
			}

			model.addAttribute("list", list);
			model.addAttribute("totalPrice", totalPrice);
		}

		return "cartlist";
	}

	@GetMapping("/addToCart")
	public String Add(@RequestParam("id") long id, Model model, HttpSession session) throws IOException {
		CartDTO cartDTO = new CartDTO();
		try {

			ProductDTO pDTO = productService.findById(id);
			UserDTO user = (UserDTO) session.getAttribute("user");

			cartDTO.setCatId(pDTO.getCatId());
			cartDTO.setProductId(pDTO.getId());
			cartDTO.setUserId(user.getId());
			cartDTO.setProductName(pDTO.getName());
			cartDTO.setPrice(pDTO.getPrice());
			cartDTO.setImage(pDTO.getImage());
			cartDTO.setQuantity(1l);

			service.Add(cartDTO);
			List<ProductDTO> list = productService.list();
			model.addAttribute("list", list);
			model.addAttribute("success", "Product Added");

		} catch (RecordNotFoundException e) {
			// TODO: handle exception
			List<ProductDTO> list = productService.list();
			model.addAttribute("list", list);
			model.addAttribute("error", "Already in Cart");
			e.printStackTrace();
			return "productlist";
		}
		return "productlist";
	}

	@PostMapping("/updateCart")
	public String updateCart(@Valid @ModelAttribute("form") CartForm form, BindingResult bindingResult, Model model,
			HttpSession session) {

		try {
			if (bindingResult.hasErrors()) {
				System.out.println("bindingResult : " + bindingResult);
				return "redirect:/cartList";
			} else {
				if (form.getId() > 0) {
					CartDTO cDTO = service.findById(form.getId());
					ProductDTO pDTO = productService.findById(form.getProductId());
					cDTO.setQuantity(DataUtility.getLong(form.getQuantity()));
					cDTO.setPrice(pDTO.getPrice() * DataUtility.getLong(form.getQuantity()));
					service.update(cDTO);
				} else {

				}

			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "redirect:/cartList";
	}

	@GetMapping("/cartDelete")
	public String cartDelete(@Valid @ModelAttribute("form") CartForm form, BindingResult bindingResult, Model model,
			@RequestParam("id") long id) {

		service.delete(id);

		return "redirect:/cartList";
	}

	@GetMapping("/getCartProductImage/{id}")
	public void getProductImage(HttpServletResponse response, @PathVariable("id") long id) throws Exception {
		response.setContentType("image/jpeg");
		Blob blb = service.getImageById(id);
		byte[] bytes = blb.getBytes(1, (int) blb.length());
		InputStream inputStream = new ByteArrayInputStream(bytes);
		IOUtils.copy(inputStream, response.getOutputStream());

	}

}
