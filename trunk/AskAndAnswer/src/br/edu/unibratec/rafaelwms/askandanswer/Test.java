package br.edu.unibratec.rafaelwms.askandanswer;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable{

	private int id_test;
	private User user;
	private String text_title;
	private String category;
	private List<Question> questions;
	private double test_value;
	
	public Test(int id_test, User user, String text_title, String category, List<Question> questions, double test_value) {
		this.setId_test(id_test);
		this.setUser(user);
		this.setText_title(text_title);
		this.setCategory(category);
		this.setQuestions(questions);
		this.setTest_value(test_value);
	}
	
	public Test(User user, String text_title, String category, List<Question> questions, double test_value) {
		this.setUser(user);
		this.setText_title(text_title);
		this.setCategory(category);
		this.setQuestions(questions);
		this.setTest_value(test_value);
	}
	
	
	public Test() {
		// TODO Auto-generated constructor stub
	}

	public int getId_test() {
		return id_test;
	}
	public void setId_test(int id_test) {
		this.id_test = id_test;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getText_title() {
		return text_title;
	}
	public void setText_title(String text_title) {
		this.text_title = text_title;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public double getTest_value() {
		return test_value;
	}

	public void setTest_value(double test_value) {
		this.test_value = test_value;
	}
	
	
	
	
	
}
