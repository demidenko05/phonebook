package org.experimentalerp.phonebook.model;

import java.io.Serializable;

public class User implements IUser, Serializable {

	private static final long serialVersionUID = -936041950889677287L;
	
	private int id;
	private String login;
	private String phone;
	
	public User(){}
	
	public User(String login, String phone) {
		this.login = login;
		this.phone = phone;
	}

	public int getId() {
		return id;
	}

	public String getLogin() {
		return login;
	}

	public String getPhone() {
		return phone;
	}

	public void setId(int id) {
		this.id=id;
	}

	public void setLogin(String login) {
		this.login=login;
	}

	public void setPhone(String phone) {
		this.phone=phone;
	}

}
