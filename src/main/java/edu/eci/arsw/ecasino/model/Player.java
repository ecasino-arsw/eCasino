package edu.eci.arsw.eCasino.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String fullName;
	private String email;
	private Integer money;

	protected Player() {
	}

	public Player(Long id, String username, String password, String fullName, String email, Integer money) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.email = email;
		this.money = money;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void withdrawMoney(int money) {
		this.money -= money;
	}
	
	public void depositMoney(int money) {
		this.money += money;
	}

	@Override
	public String toString() {
		return String.format("Player [id: %d, username: %s, full name: %s,  e-mail: %s, money: %d]", id, username,
				fullName, email, money);
	}

}