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

import it.uniroma3.siw.ProgettoSIW2019.model.Album;
import it.uniroma3.siw.ProgettoSIW2019.model.Fotografo;
import it.uniroma3.siw.ProgettoSIW2019.service.AlbumService;
import it.uniroma3.siw.ProgettoSIW2019.service.FotografoService;
import it.uniroma3.siw.ProgettoSIW2019.validator.AlbumValidator;

@Controller
public class AlbumController {

	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private AlbumValidator albumValidator;

	/* Istanzia l'oggetto Album, i cui dati sanno raccolti in un'apposita form. */
	@RequestMapping("/fotografo/{idPh}/addAlbum")
	public String addAlbum(@PathVariable ("idPh") Long idPh, Model model) {

		model.addAttribute("album", new Album());
		model.addAttribute("fotografo", this.fotografoService.fotografoPerId(idPh).get());

		return "albumForm.html";
	}

	/* Verifica i dati dell'Album appena creato.
	 * Se i suoi dati risultano corretti, procederà ad inserire l'Album nel DB.
	 * In caso contrario, si ritornerà alla form precedente per correggerli. */
	@RequestMapping(value = "/fotografo/{idPh}/album", method = RequestMethod.POST)
	public String newAlbum(@PathVariable ("idPh") Long idPh, @Valid @ModelAttribute("album") Album album, Model model, BindingResult bindingResult) {
		
		this.albumValidator.validate(album, bindingResult);
		
		if (this.albumService.alreadyExists(album)) {
			model.addAttribute("exists", "Album already exists");
			return "albumForm.html";
		}
		
		if(!bindingResult.hasErrors()) {

			this.albumService.inserisci(album);

	    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    	if (!(auth instanceof AnonymousAuthenticationToken)) {
	    		UserDetails details = (UserDetails) auth.getPrincipal();
	    		String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
	    		model.addAttribute("username", details.getUsername());
	    		model.addAttribute("role", role);
	    	}

			Fotografo ph = this.fotografoService.fotografoPerId(idPh).get();
			ph.inserisciAlbum(album);
			this.fotografoService.inserisci(ph);

			model.addAttribute("fotografo", ph);
			model.addAttribute("albums", ph.getAlbumFatti().values());
			
			return "fotografo.html";
		}else {
			return "albumForm.html";
		}
	}

	/* Ritorna la pagina dell'Album selezionato.
	 * Se l'Album non è presente nel DB, ritorna l'elenco di tutti gli Album del Fotografo, sulla base di idPh. */
	@RequestMapping(value = "/fotografo/{idPh}/album/{idA}", method = RequestMethod.GET)
	public String getFotografo(@PathVariable ("idPh") Long idPh, @PathVariable ("idA") Long idA, Model model) {

    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	if (!(auth instanceof AnonymousAuthenticationToken)) {
    		UserDetails details = (UserDetails) auth.getPrincipal();
    		String role = details.getAuthorities().iterator().next().getAuthority();     // get first authority
    		model.addAttribute("username", details.getUsername());
    		model.addAttribute("role", role);
    	}

		model.addAttribute("fotografo", this.fotografoService.fotografoPerId(idPh).get());

		if(idA!=null) {
			model.addAttribute("album", this.albumService.albumPerId(idA).get());
			return "album.html";
		}else {
			return "fotografo.html";
		}
	}
}