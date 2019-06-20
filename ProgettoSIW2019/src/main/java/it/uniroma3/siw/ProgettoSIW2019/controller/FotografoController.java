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

import it.uniroma3.siw.ProgettoSIW2019.model.Fotografo;
import it.uniroma3.siw.ProgettoSIW2019.service.FotografoService;
import it.uniroma3.siw.ProgettoSIW2019.validator.FotografoValidator;

@Controller
public class FotografoController {

	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private FotografoValidator fotografoValidator;

	/* Istanzia l'oggetto Fotografo, i cui dati sanno raccolti in un'apposita form. */
	@RequestMapping("/addFotografo")
	public String addFotografo(Model model) {

		model.addAttribute("fotografo", new Fotografo());
		return "fotografoForm.html";
	}

	/* Verifica i dati del Fotografo appena creato.
	 * Se i suoi dati risultano corretti, procederà ad inserire il Fotografo nel DB.
	 * In caso contrario, si ritornerà alla form precedente per correggerli. */
	@RequestMapping(value = "/fotografo", method = RequestMethod.POST)
	public String newFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo, Model model, BindingResult bindingResult) {

		this.fotografoValidator.validate(fotografo, bindingResult);

		if (this.fotografoService.alreadyExists(fotografo)) {
			model.addAttribute("exists", "Fotografo already exists");
			return "fotografoForm.html";
		}

		if(!bindingResult.hasErrors()) {

			this.fotografoService.inserisci(fotografo);

	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	if (!(auth instanceof AnonymousAuthenticationToken)) {
	    		UserDetails details = (UserDetails) auth.getPrincipal();
	    		String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
	    		model.addAttribute("username", details.getUsername());
	    		model.addAttribute("role", role);
	    	}

			model.addAttribute("fotografi", this.fotografoService.tutti_i_fotografi());
			return "fotografi.html";
		}else {
			return "fotografoForm.html";
		}
	}

	/* Ritorna l'elenco di tutti i Fotografi. */
	@RequestMapping(value = "/fotografi", method = RequestMethod.GET)
	public String getFotografi(Model model) {

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    		UserDetails details = (UserDetails) auth.getPrincipal();
    		String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
    		model.addAttribute("username", details.getUsername());
    		model.addAttribute("role", role);
    	}

		model.addAttribute("fotografi", this.fotografoService.tutti_i_fotografi());
		return "fotografi.html";
	}

	/* Ritorna la pagina del Fotografo selezionato.
	 * Se il Fotografo non è presente nel DB, ritorna l'elenco di tutti i Fotografi. */
	@RequestMapping(value = "/fotografo/{id}", method = RequestMethod.GET)
	public String getFotografo(@PathVariable ("id") Long id, Model model) {

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    		UserDetails details = (UserDetails) auth.getPrincipal();
    		String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
    		model.addAttribute("username", details.getUsername());
    		model.addAttribute("role", role);
    	}

		if(id!=null) {
			Fotografo ph = this.fotografoService.fotografoPerId(id).get();
			model.addAttribute("fotografo", ph);
			model.addAttribute("albums", ph.getAlbumFatti().values());
			return "fotografo.html";
		}else {
			model.addAttribute("fotografi", this.fotografoService.tutti_i_fotografi());
			return "fotografi.html";
		}
	}
}
