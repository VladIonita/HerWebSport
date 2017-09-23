package com.hubsport.domain;

public class Token {

	private String token;
	private int status;
	private String created;
	private Users users;

	public Token() {
	}

	public Token(String token, int status, String created) {
		this.token = token;
		this.status = status;
		this.created = created;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

}
