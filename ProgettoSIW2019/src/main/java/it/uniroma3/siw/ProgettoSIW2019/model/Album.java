package it.uniroma3.siw.ProgettoSIW2019.model;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import org.springframework.data.annotation.Id;

@Entity
public class Album {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String nome;

	@Column(nullable = false)
	private String descrizione;

	@OneToMany
	private Map<Long, Fotografia> fotografie;

	public Album() {

	}

	public Album(String nome, String descrizione) {

		this.nome = nome;
		this.descrizione = descrizione;
		this.fotografie = new HashMap<>();
	}

	public void inserisciNuovaFotografia(String titolo, Image immagine) {
		Fotografia f = new Fotografia(titolo, immagine);
		this.fotografie.put(f.getId(), f);

	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Map<Long, Fotografia> getFotografie() {
		return fotografie;
	}

	public void setFotografie(Map<Long, Fotografia> fotografie) {
		this.fotografie = fotografie;
	}

	public Long getId() {
		return id;
	}
}
