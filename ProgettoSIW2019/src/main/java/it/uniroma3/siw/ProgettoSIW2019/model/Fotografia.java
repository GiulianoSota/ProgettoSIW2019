package it.uniroma3.siw.ProgettoSIW2019.model;

import java.awt.Image;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import org.springframework.data.annotation.Id;

@Entity
public class Fotografia {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String titolo;

	@Column(nullable = false)
	private Image immagine;
	
	public Fotografia() {

	}

	public Fotografia(String titolo, Image immagine) {
		this.titolo = titolo;
		this.immagine = immagine;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public Image getImmagine() {
		return immagine;
	}

	public void setImmagine(Image immagine) {
		this.immagine = immagine;
	}

	public Long getId() {
		return id;
	}
}
