package it.uniroma3.siw.ProgettoSIW2019.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.ProgettoSIW2019.model.Richiesta;
import it.uniroma3.siw.ProgettoSIW2019.service.RichiestaService;
import it.uniroma3.siw.ProgettoSIW2019.validator.RichiestaValidator;

@Controller
public class RichiestaController {

	@Autowired
	private RichiestaService richiestaService;

	@Autowired
	private RichiestaValidator richiestaValidator;

	/* Istanzia l'oggetto Richiesta, i cui dati sanno raccolti in un'apposita form. */
	@RequestMapping("/addRichiesta")
	public String addRichiesta(Model model) {

		model.addAttribute("richiesta", new Richiesta());

		return "richiestaForm.html";
	}

	/* Verifica i dati della Richiesta appena creata.
	 * Se i suoi dati risultano corretti, procederà ad inserire la Richiesta nel DB.
	 * In caso contrario, si ritornerà alla form precedente per correggerli. */
	@RequestMapping(value = "/richiesta", method = RequestMethod.POST)
	public String newRichiesta(@Valid @ModelAttribute("richiesta") Richiesta richiesta, Model model, BindingResult bindingResult) {

		this.richiestaValidator.validate(richiesta, bindingResult);

		if (this.richiestaService.alreadyExists(richiesta)) {
			model.addAttribute("exists", "Fotografo already exists");
			return "richiestaForm.html";
		}

		if(!bindingResult.hasErrors()) {

			this.richiestaService.inserisci(richiesta);
			model.addAttribute("richieste", this.richiestaService.tutte_le_richieste());
			return "richieste.html";
		}else {
			return "richiestaForm.html";
		}
	}

	/* Ritorna l'elenco di tutte le Richieste. */
	@RequestMapping(value = "/richieste", method = RequestMethod.GET)
	public String getRichieste(Model model) {

		/* Recupera i dati del Funzionario (se ha già effettuato il login) */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
    		UserDetails details = (UserDetails) auth.getPrincipal();
    		String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
    		model.addAttribute("username", details.getUsername());
    		model.addAttribute("role", role);
    	}

		model.addAttribute("richieste", this.richiestaService.tutte_le_richieste());
		return "richieste.html";
	}

	/* Ritorna la pagina della Richiesta selezionata.
	 * Se la Richiesta non è presente nel DB, ritorna l'elenco di tutte le Richieste. */
	@RequestMapping(value = "/richiesta/{id}", method = RequestMethod.GET)
	public String getRichiesta(@PathVariable ("id") Long id, Model model) {

		/* Recupera i dati del Funzionario (se ha già effettuato il login) */
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
    		UserDetails details = (UserDetails) auth.getPrincipal();
    		String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
    		model.addAttribute("username", details.getUsername());
    		model.addAttribute("role", role);
    	}

		if(id!=null) {
			model.addAttribute("richiesta", this.richiestaService.richiestaPerId(id));
			return "richiesta.html";
		}else {
			model.addAttribute("richiesta", this.richiestaService.tutte_le_richieste());
			return "richieste.html";
		}
	}
}
