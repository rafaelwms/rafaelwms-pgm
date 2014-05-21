package br.edu.unibratec.rafaelwms.askandanswer;

import java.util.List;

public class Question {
	
	private int id_question;
	private Test test;
	private int number;
	private String text;
	private List<Answer> answers;
	
	
	public Question() {}
	
	public Question(int id_question, Test test, int number, String text, List<Answer> answers) {
		this.setId_question(id_question);
		this.setTest(test);
		this.setNumber(number);
		this.setText(text);
		this.setAnswers(answers);
	}
	
	public Question(Test test, int number, String text, List<Answer> answers) {
		this.setTest(test);
		this.setNumber(number);
		this.setText(text);
		this.setAnswers(answers);
	}
	
	
	public Test getTest() {
		return test;
	}
	public void setTest(Test test) {
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
