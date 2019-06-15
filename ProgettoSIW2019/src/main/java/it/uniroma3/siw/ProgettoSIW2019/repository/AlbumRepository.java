package it.uniroma3.siw.ProgettoSIW2019.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.ProgettoSIW2019.model.Album;

public interface AlbumRepository extends CrudRepository<Album, Long>{

	public List<Album> findByNome(String nome);
	
}
