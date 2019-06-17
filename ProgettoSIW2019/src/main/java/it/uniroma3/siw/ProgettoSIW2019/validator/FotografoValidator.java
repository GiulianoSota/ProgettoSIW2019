package it.uniroma3.siw.ProgettoSIW2019.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.siw.ProgettoSIW2019.model.Fotografo;

@Component
public class FotografoValidator implements Validator {
	@Autowired
	private FotografoService fotografoService;

	@Override
	public void validate(Object o, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "cognome", "required");
		
		if (this.fotografoService.alreadyExists((Fotografo) o)) {
			e.reject("duplicato");
		}
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return Fotografo.class.equals(aClass);
	}

}