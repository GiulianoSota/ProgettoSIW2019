package it.uniroma3.siw.ProgettoSIW2019.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.siw.ProgettoSIW2019.model.Funzionario;
import it.uniroma3.siw.ProgettoSIW2019.service.FunzionarioService;

@Component
public class FunzionarioValidator implements Validator {
	@Autowired
	private FunzionarioService funzionarioService;

	@Override
	public void validate(Object o, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "email", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "username", "required");
		
		if (this.funzionarioService.alreadyExists((Funzionario) o)) {
			e.reject("duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Funzionario.class.equals(aClass);
	}

}