package it.uniroma3.siw.ProgettoSIW2019.model;

import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import org.springframework.data.annotation.Id;

@Entity
public class Richiesta {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String nomeCliente;

	@Column(nullable = false)
	private String cognomeCliente;

	@Column(nullable = false, unique = true)
	private String emailCliente;

	@Column(nullable = false, unique = true)
	private String telefonoCliente;

	@Column(nullable = false)
	private String descrizione;

	@OneToMany
	private Map<Long, Fotografia> fotografieScelte;

	public Richiesta() {

	}

	public Richiesta(String nmC, String cgC, String emC, String tlC, String descrizione,
						Map<Long, Fotografia> fS) {
		
		this.nomeCliente = nmC;
		this.cognomeCliente = cgC;
		this.emailCliente = emC;
		this.telefonoCliente = tlC;
		this.descrizione = descrizione;
		this.fotografieScelte = new HashMap<>();
		this.fotografieScelte = fS;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getCognomeCliente() {
		return cognomeCliente;
	}

	public void setCognomeCliente(String cognomeCliente) {
		this.cognomeCliente = cognomeCliente;
	}

	public String getEmailCliente() {
		return emailCliente;
	}

	public void setEmailCliente(String emailCliente) {
		this.emailCliente = emailCliente;
	}

	public String getTelefonoCliente() {
		return telefonoCliente;
	}

	public void setTelefonoCliente(String telefonoCliente) {
		this.telefonoCliente = telefonoCliente;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Map<Long, Fotografia> getFotografieScelte() {
		return fotografieScelte;
	}

	public void setFotografieScelte(Map<Long, Fotografia> fotografieScelte) {
		this.fotografieScelte = fotografieScelte;
	}

	public Long getId() {
		return id;
	}
}