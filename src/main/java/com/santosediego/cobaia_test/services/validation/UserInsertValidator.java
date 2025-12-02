package com.santosediego.cobaia_test.services.validation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.santosediego.cobaia_test.dto.UserInsertDTO;
import com.santosediego.cobaia_test.entities.User;
import com.santosediego.cobaia_test.entities.enums.Role;
import com.santosediego.cobaia_test.repositories.UserRepository;
import com.santosediego.cobaia_test.resources.exceptions.FieldMessage;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

	@Autowired
	private UserRepository repository;

	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {

		List<FieldMessage> list = new ArrayList<>();
		String email = dto.getEmail();

		if (email != null && !email.isBlank()) {
			User user = repository.findByEmail(email);
			if (user != null) {
				list.add(new FieldMessage("email", "Email já cadastrado"));
			}
		}

		User existingByUsername = repository.findByUsername(dto.getUsername());
		if (existingByUsername != null) {
			list.add(new FieldMessage("username", "Nome de usuário já cadastrado"));
		}

		boolean roleValida = false;
		for (Role r : Role.values()) {
			if (r.getId().equals(dto.getRole())) {
				roleValida = true;
				break;
			}
		}
		if (!roleValida) {
			list.add(new FieldMessage("role", "Role inválida"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
