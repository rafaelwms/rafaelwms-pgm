package br.edu.unibratec.rafaelwms.askandanswer;

import java.io.Serializable;

public class User implements Serializable{
	
	private int id_user;
	private String login_user;
	private String password_user;
	private boolean master_user;
	
	public User(){}
	
	public User(String login_user, String password_user, boolean master_user) {
		this.setLogin_user(login_user);
		this.setPassword_user(password_user);
		this.setMaster_user(master_user);
	}
	
	public User(int id_user, String login_user, String password_user, boolean master_user) {
		this.setId_user(id_user);
		this.setLogin_user(login_user);
		this.setPassword_user(password_user);
		this.setMaster_user(master_user);
	}
	

	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public String getLogin_user() {
		return login_user;
	}
	public void setLogin_user(String login_user) {
		this.login_user = login_user;
	}
	public String getPassword_user() {
		return password_user;
	}
	public void setPassword_user(String password_user) {
		this.password_user = password_user;
	}
	public boolean isMaster_user() {
		return master_user;
	}
	public void setMaster_user(boolean master_user) {
		this.master_user = master_user;
	}

}
