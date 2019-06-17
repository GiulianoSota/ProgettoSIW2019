package it.uniroma3.siw.ProgettoSIW2019.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.ProgettoSIW2019.service.RichiestaService;
import it.uniroma3.siw.ProgettoSIW2019.service.RichiestaValidator;

@Controller
public class RichiestaController {

	@Autowired
	private RichiestaService richiestaService;

	@Autowired
	private RichiestaValidator richiestaValidator;

}
