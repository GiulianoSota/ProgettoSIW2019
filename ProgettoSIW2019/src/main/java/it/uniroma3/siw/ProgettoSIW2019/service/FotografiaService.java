package it.uniroma3.siw.ProgettoSIW2019.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.ProgettoSIW2019.model.Fotografia;
import it.uniroma3.siw.ProgettoSIW2019.repository.FotografiaRepository;

@Service
public class FotografiaService {
	@Autowired
	private FotografiaRepository fotografiaRepository;
	
	@Transactional
	public Fotografia inserisci(Fotografia fotografia) {
		return fotografiaRepository.save(fotografia);
	}
	
	@Transactional
	public List<Fotografia> tutte_le_fotografie() {
		return (List<Fotografia>) fotografiaRepository.findAll();
	}

	@Transactional
	public Optional<Fotografia> fotografiaPerId(Long id) {
		return fotografiaRepository.findById(id);
	}

	@Transactional
	public List<Fotografia> fotografiePerNome(String titolo) {
		return fotografiaRepository.findByTitolo(titolo);
	}
	
	public boolean alreadyExists(Fotografia fotografia) {
		return fotografiaRepository.findByTitolo(fotografia.getTitolo()) != null ;
	}

}
