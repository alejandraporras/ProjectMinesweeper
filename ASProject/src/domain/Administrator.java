package domain;

import java.io.Serializable;

import javax.persistence.Entity;

@Entity
public class Administrator extends RegisteredUser implements Serializable  {

	private String telephone;
	public Administrator(){
		super();
	}
	public Administrator(String name, String surname, String username, String password){
		super(name, surname, username, password);
	}
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
