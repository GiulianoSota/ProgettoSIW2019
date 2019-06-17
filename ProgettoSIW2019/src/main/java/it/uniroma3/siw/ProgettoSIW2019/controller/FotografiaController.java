package it.uniroma3.siw.ProgettoSIW2019.controller;

import java.util.List;

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
		model.addAttribute("album", this.albumService.albumPerId(idA));
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
			//TODO aggiunta al model della lista (aggiornata) di Fotografie dell'Album
			return "fotografie.html";
		}else {
			return "fotografiaForm.html";
		}
	}

	/* Ritorna l'elenco di tutte le Fotografie di un dato Album. */
	@RequestMapping(value = "/fotografo/{idPh}/album/{idA}/fotografie", method = RequestMethod.GET)
	public String getFotografi(Model model) {

		//TODO selezione di tutte le Fotografie di un Album, in base a idA
		return "fotografie.html";
	}

	/* Ritorna la pagina della Fotografia selezionata.
	 * Se la Fotografia non è presente nel DB, ritorna l'elenco di tutte le Fotografie dell'Album, sulla base di idA. */
	@RequestMapping(value = "/fotografo/{idPh}/album/{idA}/fotografia/{idPic}", method = RequestMethod.GET)
	public String getFotografo(@PathVariable ("idA") Long idA, @PathVariable ("idPic") Long idPic, Model model) {

		if(idPic!=null) {
			model.addAttribute("fotografia", this.fotografiaService.fotografiaPerId(idPic));
			return "fotografia.html";
		}else {
			//TODO selezione di tutte le Fotografie di un Album, in base a idA
			return "fotografie.html";
		}
	}
}
