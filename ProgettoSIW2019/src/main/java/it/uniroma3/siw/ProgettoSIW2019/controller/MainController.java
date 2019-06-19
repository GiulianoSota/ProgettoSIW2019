package it.uniroma3.siw.ProgettoSIW2019.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	/* Questo metodo verrà chiamato quando l'utente manderà una richiesta GET all'URL '/' oppure '/index'
	 * Questo metodo prepara e invia la vista sulla pagina principale. */
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String getHomePage(Model model) {

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    		UserDetails details = (UserDetails) auth.getPrincipal();
    		String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
    		model.addAttribute("username", details.getUsername());
    		model.addAttribute("role", role);
    	}

		return "index.html";
	}
}
