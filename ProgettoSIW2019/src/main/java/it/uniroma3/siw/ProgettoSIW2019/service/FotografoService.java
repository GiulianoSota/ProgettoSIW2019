package it.uniroma3.siw.ProgettoSIW2019.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.ProgettoSIW2019.model.Fotografo;
import it.uniroma3.siw.ProgettoSIW2019.repository.FotografoRepository;

@Service
public class FotografoService {
	@Autowired
	private FotografoRepository fotografoRepository;

	@Transactional
	public Fotografo inserisci(Fotografo fotografo) {
		return fotografoRepository.save(fotografo);
	}

	@Transactional
	public List<Fotografo> tutti_i_fotografi() {
		return (List<Fotografo>) fotografoRepository.findAll();
	}

	@Transactional
	public Optional<Fotografo> fotografoPerId(Long id) {
		return fotografoRepository.findById(id);
	}

	@Transactional
	public List<Fotografo> fotografoPerNome(String nome) {
		return fotografoRepository.findByNome(nome);
	}

	@Transactional
	public List<Fotografo> fotografoPerNomeAndCognome(String nome, String cognome) {
		return fotografoRepository.findByNomeAndCognome(nome, cognome);
	}
	
	public boolean alreadyExists(Fotografo fotografo) {
		return fotografoRepository.findByNomeAndCognome(fotografo.getNome(), fotografo.getCognome()) != null ;
	}
}