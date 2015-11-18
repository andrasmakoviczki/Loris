package edu.elte.spring.loris.frontend.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import edu.elte.spring.loris.frontend.model.UserModel;

public class PasswordValidator implements ConstraintValidator<PasswordMatch, Object> {
	
	@Override
	public void initialize(PasswordMatch constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object obj, ConstraintValidatorContext context) {
		UserModel user = (UserModel) obj;
		return user.getPassword().equals(user.getPasswordConfirm());
	}
}
