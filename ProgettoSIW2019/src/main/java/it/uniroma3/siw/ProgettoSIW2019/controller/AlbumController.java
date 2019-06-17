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

import it.uniroma3.siw.ProgettoSIW2019.model.Album;
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
	@RequestMapping("/fotografo/{id}/addAlbum")
	public String addAlbum(@PathVariable ("id") Long id, Model model) {

		model.addAttribute("album", new Album());
		model.addAttribute("fotografo", this.fotografoService.fotografoPerId(id));
		return "albumForm.html";
	}

	/* Verifica i dati dell'Album appena creato.
	 * Se i suoi dati risultano corretti, procederà ad inserire l'Album nel DB.
	 * In caso contrario, si ritornerà alla form precedente per correggerli. */
	@RequestMapping(value = "/fotografo/{id}/album", method = RequestMethod.POST)
	public String newAlbum(@PathVariable ("id") Long id, @Valid @ModelAttribute("album") Album album, Model model, BindingResult bindingResult) {

		this.albumValidator.validate(album, bindingResult);

		if (this.albumService.alreadyExists(album)) {
			model.addAttribute("exists", "Album already exists");
			return "albumForm.html";
		}
		if(!bindingResult.hasErrors()) {
			this.albumService.inserisci(album);
			//TODO aggiornamento della lista di Album del Fotografo
			//TODO aggiunta al model dell'attributo "albums" con la lista (aggiornata) degli Album del Fotografo
			return "albums.html";
		}else {
			return "albumForm.html";
		}
	}

	/* Ritorna l'elenco di tutti gli Album di un dato Fotografo. */
	@RequestMapping(value = "/fotografo/{idPh}/albums", method = RequestMethod.GET)
	public String getFotografi(Model model) {

		//TODO selezione di tutti gli Album di un Fotografo, in base a idPh
		return "albums.html";
	}

	/* Ritorna la pagina dell'Album selezionato.
	 * Se l'Album non è presente nel DB, ritorna l'elenco di tutti gli Album del Fotografo, sulla base di idPh. */
	@RequestMapping(value = "/fotografo/{idPh}/album/{idA}", method = RequestMethod.GET)
	public String getFotografo(@PathVariable ("idPh") Long idPh, @PathVariable ("idA") Long idA, Model model) {

		if(idA!=null) {
			model.addAttribute("album", this.albumService.albumPerId(idA));
			return "album.html";
		}else {
			//TODO selezione di tutti gli Album di un Fotografo, in base a idPh
			return "albums.html";
		}
	}
}
