package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public class RegisteredUser implements Serializable{ 
	
	private String name;
	private String surname;
	@Id
	private String username;
	private String password;
	
	public RegisteredUser(){
		
	}
	
	public RegisteredUser(String name, String surname, String username, String password){
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
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
	public boolean isPlayer(){
		return false;
	}
	@Override
	public String toString(){
		
		return ("Name: " + this.getName() + " Surname: " + this.getSurname() + " username: " 
				+ this.getUsername() + " password: " + this.getPassword());

	}
}
