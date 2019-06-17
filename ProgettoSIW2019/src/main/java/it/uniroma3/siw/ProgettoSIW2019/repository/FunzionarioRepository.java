package it.uniroma3.siw.ProgettoSIW2019.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import it.uniroma3.siw.ProgettoSIW2019.model.Funzionario;

public interface FunzionarioRepository extends CrudRepository<Funzionario, Long> {

	public List<Funzionario> findByUsername(String username);
	public List<Funzionario> findByEmail(String email);
	public List<Funzionario> findByEmailAndPassword(String email,String password); //vedi FunzionarioService, metodo doLogin.

}
