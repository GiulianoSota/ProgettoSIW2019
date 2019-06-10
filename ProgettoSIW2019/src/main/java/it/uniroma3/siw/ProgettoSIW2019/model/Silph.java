package it.uniroma3.siw.ProgettoSIW2019.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Silph {

	@OneToMany(mappedBy = "silph")
	private Map<String, Funzionario> funzionari;

	@OneToMany(mappedBy = "silph")
	private Map<String, Fotografo> fotografi;

	@OneToMany(mappedBy = "silph")
	private List<Richiesta> richieste;
	
	public Silph() {
		this.funzionari = new HashMap<>();
		this.fotografi = new HashMap<>();
		this.richieste = new ArrayList<>();
	}

	public Map<String, Funzionario> getFunzionari() {
		return funzionari;
	}

	public void setFunzionari(Map<String, Funzionario> funzionari) {
		this.funzionari = funzionari;
	}

	public Map<String, Fotografo> getFotografi() {
		return fotografi;
	}

	public void setFotografi(Map<String, Fotografo> fotografi) {
		this.fotografi = fotografi;
	}

	public List<Richiesta> getRichieste() {
		return richieste;
	}

	public void setRichieste(List<Richiesta> richieste) {
		this.richieste = richieste;
	}
}