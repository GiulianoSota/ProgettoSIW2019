package it.uniroma3.siw.ProgettoSIW2019.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.ProgettoSIW2019.model.Fotografia;
import it.uniroma3.siw.ProgettoSIW2019.service.AlbumService;
import it.uniroma3.siw.ProgettoSIW2019.service.FotografiaService;
import it.uniroma3.siw.ProgettoSIW2019.validator.FotografiaValidator;

@Controller
public class FotografiaController {

	@Autowired
	private AlbumService albumService;

	@Autowired
	private FotografiaService fotografiaService;

	@Autowired
	private FotografiaValidator fotografiaValidator;

	/* Istanzia l'oggetto Fotografia, i cui dati sanno raccolti in un'apposita form. */
	@RequestMapping("/fotografo/{idPh}/album/{idA}/addFotografia")
	public String addFotografia(@PathVariable ("idA") Long idA, Model model) {

		model.addAttribute("fotografia", new Fotografia());
		model.addAttribute("album", this.albumService.albumPerId(idA).get());
		return "fotografiaForm.html";
	}

	/* Verifica i dati della Fotografia appena creato.
	 * Se i suoi dati risultano corretti, procederà ad inserire la Fotografia nel DB.
	 * In caso contrario, si ritornerà alla form precedente per correggerli. */
	@RequestMapping(value = "/fotografo/{idPh}/album/{idA}/fotografia", method = RequestMethod.POST)
	public String newAlbum(@PathVariable ("idA") Long idA, @Valid @ModelAttribute("fotografia") Fotografia fotografia, Model model, BindingResult bindingResult) {

		this.fotografiaValidator.validate(fotografia, bindingResult);

		if (this.fotografiaService.alreadyExists(fotografia)) {
			model.addAttribute("exists", "Fotografia already exists");
			return "fotografiaForm.html";
		}
		if(!bindingResult.hasErrors()) {
			this.fotografiaService.inserisci(fotografia);
			//TODO aggiornamento della lista di Fotografie dell'Album
			model.addAttribute("album", this.albumService.albumPerId(idA).get());
			return "fotografie.html";
		}else {
			return "fotografiaForm.html";
		}
	}
}
