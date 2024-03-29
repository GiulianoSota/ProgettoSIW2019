package it.uniroma3.siw.ProgettoSIW2019.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import it.uniroma3.siw.ProgettoSIW2019.model.Fotografia;
import it.uniroma3.siw.ProgettoSIW2019.service.FotografiaService;

@Component
public class FotografiaValidator implements Validator {
	@Autowired
	private FotografiaService fotografiaService;
	
	@Override
	public void validate(Object o, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "titolo", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "immagine", "required");
		
		if (this.fotografiaService.alreadyExists((Fotografia) o)) {
			e.reject("duplicato");
		}
		
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Fotografia.class.equals(aClass);	
	
	}

}