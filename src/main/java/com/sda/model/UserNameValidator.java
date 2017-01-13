package com.sda.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameValidator implements ConstraintValidator<UserName, String>{

	@Override
	public void initialize(UserName username) {}

	@Override
	public boolean isValid(String username, ConstraintValidatorContext ctx) {
		return username.matches("[a-zA-Z0-9]+");
	}

}
