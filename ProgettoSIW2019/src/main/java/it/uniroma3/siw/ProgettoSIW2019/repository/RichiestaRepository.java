package it.uniroma3.siw.ProgettoSIW2019.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.ProgettoSIW2019.model.Richiesta;

public interface RichiestaRepository extends CrudRepository<Richiesta, Long> {
	
	public List<Richiesta> findByEmailCliente(String emailCliente);
	public List<Richiesta> findByTelefonoCliente(String telefonoCliente);
	//comodo anche il metodo findAll() per avere la lista delle richieste di tutti i clienti, già presente in CrudRepository.

}
