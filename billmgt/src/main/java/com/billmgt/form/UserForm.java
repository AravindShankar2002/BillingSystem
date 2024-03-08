package com.billmgt.form;



import java.util.Date;


import javax.validation.constraints.NotEmpty;

import com.billmgt.dto.BaseDTO;
import com.billmgt.dto.UserDTO;
import com.billmgt.utility.DataUtility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm extends BaseDTO {
	
	@NotEmpty(message = "First name is required")
	private String firstName;

	@NotEmpty(message = "Last name is required")
	private String lastName;
	
	@NotEmpty(message = "Date of birth is required")
	private String dob;
	
	@NotEmpty(message = "Gender is required")
	private String gender;
	
	@NotEmpty(message = "Email is required")
	private String email;
	
	@NotEmpty(message = "Passowrd is required")
	private String password;
	
	@NotEmpty(message = "Phone number is required")
	private String phoneNumber;
	
	
	
	public UserDTO getDTO() {
		UserDTO bean=new UserDTO();
		bean.setId(id);
		bean.setFirstName(firstName);
		bean.setLastName(lastName);
		bean.setDob(dob);
		bean.setGender(gender);
		bean.setEmail(email);
		bean.setPassword(password);
		bean.setPhoneNumber(phoneNumber);

		return bean;
	}

	public void populate(UserDTO bean) {
		id = bean.getId();
		firstName=bean.getFirstName();
		lastName=bean.getLastName();
		dob = bean.getDob();
		gender = bean.getGender();
		email = bean.getEmail();
		password = bean.getPassword();
		phoneNumber = bean.getPhoneNumber();

	}

	@Override
	public String toString() {
		return "UserForm [firstName=" + firstName + ", lastName=" + lastName + ", dob=" + dob + ", gender=" + gender
				+ ", email=" + email + ", password=" + password + ", phoneNumber="
				+ phoneNumber + "]";
	}
	
	

	
	

}
