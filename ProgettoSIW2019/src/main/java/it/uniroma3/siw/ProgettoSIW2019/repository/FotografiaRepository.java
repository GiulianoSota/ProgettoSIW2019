package it.uniroma3.siw.ProgettoSIW2019.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.ProgettoSIW2019.model.Fotografia;

public interface FotografiaRepository  extends CrudRepository<Fotografia, Long> {
	
	public List<Fotografia> findByTitolo(String titolo);

}
