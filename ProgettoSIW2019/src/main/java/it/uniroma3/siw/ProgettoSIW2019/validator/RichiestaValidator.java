package it.uniroma3.siw.ProgettoSIW2019.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.siw.ProgettoSIW2019.model.Richiesta;

@Component
public class RichiestaValidator implements Validator {

	@Override
	public void validate(Object o, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "nomeCliente", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "cognomeCliente", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "emailCliente", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "telefonoCliente", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "descrizione", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "fotografieScelte", "required");

	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Richiesta.class.equals(aClass);
	}

}