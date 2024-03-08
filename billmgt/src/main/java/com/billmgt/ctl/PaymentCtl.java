package com.billmgt.ctl;

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
import com.billmgt.dto.PaymentDTO;
import com.billmgt.dto.UserDTO;
import com.billmgt.form.PaymentForm;
import com.billmgt.service.BillService;
import com.billmgt.service.CartService;
import com.billmgt.service.PaymentService;





@Controller
public class PaymentCtl {
	
	@Autowired
	private PaymentService service;
	
	@Autowired
	private BillService billService;
	
	@Autowired
	private CartService cartService;
	
	@GetMapping("/payment")
	public String paymentpage(@ModelAttribute("form")PaymentForm form, Model model, @RequestParam("totalPrice") long totalPrice, @RequestParam("billId") long billId) {
		if(totalPrice>0) {
			model.addAttribute("totalPrice", totalPrice);
			model.addAttribute("billId", billId);
		}
		return "payment";
	}
	
	@GetMapping("/cartpayment")
	public String cartpayment(@ModelAttribute("form")PaymentForm form, Model model, @RequestParam("totalPrice") long totalPrice) {
		if(totalPrice>0) {
			model.addAttribute("totalPrice", totalPrice);
		
		}
		return "payment";
	}
	

	
	@PostMapping("/addPayment")
	public String add(@Valid @ModelAttribute("form")PaymentForm form, BindingResult bindingResult, Model model, HttpSession session ) {

		
		if(bindingResult.hasErrors()) {
			return "payment";
		}else {
			PaymentDTO dto = form.getDTO();
			System.out.println("Id:" +dto.getId());
			UserDTO user = (UserDTO)session.getAttribute("user");
			dto.setEmail(user.getEmail());
			
			if(dto.getBillId()>0) {
				BillDTO bDto = billService.findById(dto.getBillId());
				bDto.setStatus("Paid");
				billService.update(bDto);
			}
			
			service.Add(dto);
			model.addAttribute("success", "Payment Done!");
			cartService.deleteCart();
		}
		return "payment";
	}
	
	@GetMapping("/paymentlist")
	public String list(@ModelAttribute("form")PaymentForm form, Model model, HttpSession session) {
		
		UserDTO user = (UserDTO) session.getAttribute("user");
		List<PaymentDTO> list =null;
		if(user.getUserRole().equals("User")) {
			list = service.list(user.getEmail());
		}else {
			list = service.list();
		}
		 
        model.addAttribute("list", list);
		return "paymentlist";
	}

}
