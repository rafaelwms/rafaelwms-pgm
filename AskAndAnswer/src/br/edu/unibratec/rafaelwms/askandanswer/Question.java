package br.edu.unibratec.rafaelwms.askandanswer;

import java.io.Serializable;
import java.util.List;

public class Question implements Serializable{
	
	private int id_question;
	private int test;
	private int number;
	private String text;
	private List<Answer> answers;
	
	
	public Question() {}
	
	public Question(int id_question, Test test, int number, String text, List<Answer> answers) {
		this.setId_question(id_question);
		this.setTest(test.getId_test());
		this.setNumber(number);
		this.setText(text);
		this.setAnswers(answers);
	}
	
	public Question(Test test, int number, String text, List<Answer> answers) {
		this.setTest(test.getId_test());
		this.setNumber(number);
		this.setText(text);
		this.setAnswers(answers);
	}
	
	
	public int getTest() {
		return test;
	}
	public void setTest(int test) {
		this.test = test;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Answer> getAnswers() {
		return answers;
	}
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public int getId_question() {
		return id_question;
	}

	public void setId_question(int id_question) {
		this.id_question = id_question;
	}
	
	

}
