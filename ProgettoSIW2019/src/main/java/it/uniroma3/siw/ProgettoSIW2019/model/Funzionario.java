package it.uniroma3.siw.ProgettoSIW2019.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import org.springframework.data.annotation.Id;

@Entity
public class Funzionario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false)
	private String password;

	@ManyToOne
	private Silph silph;

	public Funzionario() {

	}

	public Funzionario(String email, String username, String password, Silph silph) {
		
		this.email = email;
		this.username = username;
		this.password = password;
		this.silph = silph;

	}

	public boolean checkPassword() {return false;} //TODO

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public Silph getSilph() {
		return silph;
	}
}