package it.uniroma3.siw.ProgettoSIW2019.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.ProgettoSIW2019.model.Funzionario;
import it.uniroma3.siw.ProgettoSIW2019.repository.FunzionarioRepository;

@Service
public class FunzionarioService {
	@Autowired
	private FunzionarioRepository funzionarioRepository;
	
	@Transactional
	public Funzionario inserisci(Funzionario funzionario) {
		return funzionarioRepository.save(funzionario);
	}
	
	@Transactional
	public List<Funzionario> tutti_i_funzionari() {
		return (List<Funzionario>) funzionarioRepository.findAll();
	}
	
	@Transactional
	public Optional<Funzionario> funzionarioPerId(Long id) {
		return funzionarioRepository.findById(id);
	}

	@Transactional
	public List<Funzionario> funzionarioPerEmail(String email) {
		return funzionarioRepository.findByEmail(email);
	}

	@Transactional
	public List<Funzionario> funzionarioPerUsername(String username) {
		return funzionarioRepository.findByUsername(username);
	}
	
	//In fase di registrazione
	public boolean alreadyExists(Funzionario funzionario) {
		return (funzionarioRepository.findByEmail(funzionario.getEmail()) != null || funzionarioRepository.findByUsername(funzionario.getUsername()) != null); 
		//Un funzionario, in fase di registrazione, non deve essere registrato se nel db è già presente email e username, solo email o solo username. 
	}

}
