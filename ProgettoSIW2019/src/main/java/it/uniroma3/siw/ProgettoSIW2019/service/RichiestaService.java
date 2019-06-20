package it.uniroma3.siw.ProgettoSIW2019.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.ProgettoSIW2019.model.Richiesta;
import it.uniroma3.siw.ProgettoSIW2019.repository.RichiestaRepository;

@Service
public class RichiestaService {
	@Autowired
	private RichiestaRepository richiestaRepository;
	
	@Transactional
	public Richiesta inserisci(Richiesta richiesta) {
		return richiestaRepository.save(richiesta);
	}
	
	@Transactional
	public List<Richiesta> tutte_le_richieste() {
		return (List<Richiesta>) richiestaRepository.findAll();
	}
	
	@Transactional
	public Optional<Richiesta> richiestaPerId(Long id) {
		return richiestaRepository.findById(id);
	}
	
	@Transactional
	public List<Richiesta> richiestaPerEmailCliente(String emailCliente) {
		return richiestaRepository.findByEmailCliente(emailCliente);
	}

	@Transactional
	public List<Richiesta> richiestaPerTelefonoCliente(String telefonoCliente) {
		return richiestaRepository.findByTelefonoCliente(telefonoCliente);
	}
	
	public boolean alreadyExists(Richiesta richiesta) {
		return richiestaRepository.findByEmailCliente(richiesta.getEmailCliente()) != null ;
	}
}
