package edu.eci.arsw.eCasino.model;

import java.io.Serializable;
import java.util.UUID;

public class Player implements Serializable {

	private Integer id;
	private String username;
	private String password;
	private String fullName;
	private Integer money;
	
	public Player() {
		
	}
	
	public Player(Integer id, String username, String password, String fullName, Integer money) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.fullName = fullName;
		this.money = money;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}

}
