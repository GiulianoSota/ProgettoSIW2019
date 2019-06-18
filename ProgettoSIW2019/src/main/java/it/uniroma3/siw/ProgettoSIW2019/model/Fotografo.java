package it.uniroma3.siw.ProgettoSIW2019.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;

import javax.persistence.Id;

@Entity
public class Fotografo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String cognome;
	
	@OneToMany
	private Map<Long, Album> albumFatti;

	public Fotografo() {

	}

	public Fotografo(String nome, String cognome) {

		this.nome = nome;
		this.cognome = cognome;
		this.albumFatti = new HashMap<>();

	}

	public void inserisciAlbum(Album a) {
		this.albumFatti.put(a.getId(), a);
	}

	public void creaNuovoAlbum(String nome, String descrizione) {
		Album a = new Album(nome, descrizione);
		this.albumFatti.put(a.getId(), a);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Map<Long, Album> getAlbumFatti() {
		return albumFatti;
	}

	public void setAlbumFatti(Map<Long, Album> albumFatti) {
		this.albumFatti = albumFatti;
	}

	public Long getId() {
		return id;
	}
}
