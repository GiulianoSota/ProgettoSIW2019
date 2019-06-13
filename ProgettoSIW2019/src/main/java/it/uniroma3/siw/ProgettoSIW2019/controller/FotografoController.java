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

import it.uniroma3.siw.ProgettoSIW2019.model.Fotografo;
import it.uniroma3.siw.ProgettoSIW2019.services.FotografoService;
import it.uniroma3.siw.ProgettoSIW2019.services.FotografoValidator;

@Controller
public class FotografoController {

	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private FotografoValidator fotografoValidator;
	
	@RequestMapping("/addFotografo")
	public String addFotografo(Model model) {
		model.addAttribute("fotografo", new Fotografo());
		return "fotografoForm.html";
	}

	@RequestMapping(value = "/fotografo", method = RequestMethod.POST)
	public String newFotografo(@Valid @ModelAttribute("fotografo") Fotografo fotografo, Model model, BindingResult bindingResult) {
		this.fotografoValidator.validate(fotografo, bindingResult);

		if (this.fotografoService.alreadyExists(fotografo)) {
			model.addAttribute("exists", "Fotografo already exists");
			return "fotografoForm";
		}
		if(!bindingResult.hasErrors()) {
			this.fotografoService.inserisci(fotografo);
			model.addAttribute("fotografi", this.fotografoService.tutti());
			return "fotografi.html";
		}else {
			return "fotografoForm.html";
		}
	}

		@RequestMapping(value = "/fotografo/{id}", method = RequestMethod.GET)
		public String getFotografo(@PathVariable ("id") Long id, Model model) {
			if(id!=null) {
				model.addAttribute("fotografo", this.fotografoService.FotografoPerId(id));
				return "fotografo.html";
			}else {
				model.addAttribute("fotografi", this.fotografoService.tutti());
				return "fotografi.html";
			}
		}
	}
}
