package it.uniroma3.siw.ProgettoSIW2019.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import it.uniroma3.siw.ProgettoSIW2019.service.FunzionarioService;

@Controller
public class FunzionarioController {

	@Autowired
	private FunzionarioService funzionarioService;


}
