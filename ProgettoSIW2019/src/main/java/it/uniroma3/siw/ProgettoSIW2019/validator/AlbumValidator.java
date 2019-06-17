package it.uniroma3.siw.ProgettoSIW2019.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import it.uniroma3.siw.ProgettoSIW2019.model.Album;

@Component
public class AlbumValidator implements Validator {
	@Autowired
	private AlbumService albumService;
	
	@Override
	public void validate(Object o, Errors e) {
		ValidationUtils.rejectIfEmptyOrWhitespace(e, "nome", "required");
		
		if (this.albumService.alreadyExists((Album) o)) {
			e.reject("duplicato");
		}
		
	}
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Album.class.equals(aClass);
	}
	
	

}