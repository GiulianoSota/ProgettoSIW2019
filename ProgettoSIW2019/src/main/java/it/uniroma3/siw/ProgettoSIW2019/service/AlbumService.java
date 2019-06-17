package it.uniroma3.siw.ProgettoSIW2019.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.uniroma3.siw.ProgettoSIW2019.model.Album;
import it.uniroma3.siw.ProgettoSIW2019.repository.AlbumRepository;

@Service
public class AlbumService {
	@Autowired
	private AlbumRepository albumRepository;
	
	@Transactional
	public Album inserisci(Album album) {
		return albumRepository.save(album);
	}
	
	@Transactional
	public List<Album> tutti_gli_album() {
		return (List<Album>) albumRepository.findAll();
	}

	@Transactional
	public Optional<Album> albumPerId(Long id) {
		return albumRepository.findById(id);
	}
	
	@Transactional
	public List<Album> albumPerNome(String nome) {
		return albumRepository.findByNome(nome);
	}
	
	public boolean alreadyExists(Album album) {
		return albumRepository.findByNome(album.getNome()) != null;
	}
}
