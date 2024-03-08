package com.billmgt.form;

import javax.validation.constraints.NotEmpty;

import com.billmgt.dto.BaseDTO;
import com.billmgt.dto.PaymentDTO;
import com.billmgt.utility.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentForm extends BaseDTO{
	
	@NotEmpty(message = "Card Number is required")
	private String cardNumber;
	
	@NotEmpty(message = "Name On Card is required")
	private String nameOnCard;
	
	@NotEmpty(message = "Expiry Date is required")
	private String expireDate;
	
	private long totalPrice;
	
	private String billId;
	
	public PaymentDTO getDTO() {
		PaymentDTO bean=new PaymentDTO();
		bean.setId(id);
		bean.setCardNumber(cardNumber);
		bean.setNameOnCard(nameOnCard);
		bean.setExpireDate(expireDate);
		bean.setTotalPrice(totalPrice);
		bean.setBillId(DataUtility.getLong(billId));


		return bean;
	}

	public void populate(PaymentDTO bean) {
		id = bean.getId();
		cardNumber = bean.getCardNumber();
		nameOnCard = bean.getNameOnCard();
		expireDate = bean.getExpireDate();
		totalPrice = bean.getTotalPrice();
		billId = DataUtility.getStringData(bean.getBillId());
	}
}
