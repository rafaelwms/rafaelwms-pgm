package br.edu.unibratec.rafaelwms.askandanswer;

import java.io.Serializable;

public class Result implements Serializable{
	
	private User user;
	private Test test;
	private double result;
	
	public Result(){}
	
	public Result(User user, Test test, double result) {
		this.setUser(user);
		this.setTest(test);
		this.setResult(result);
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
		this.test = test;
	}
	public double getResult() {
		return result;
	}
	public void setResult(double result) {
		this.result = result;
	}
	
	

}
