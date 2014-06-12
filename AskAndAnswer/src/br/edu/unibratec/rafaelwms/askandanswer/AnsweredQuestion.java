package br.edu.unibratec.rafaelwms.askandanswer;

import java.io.Serializable;

public class AnsweredQuestion implements Serializable{
	
	private User user;
	private Question question;
	private boolean checkedAnswer1;
	private boolean checkedAnswer2;
	private boolean checkedAnswer3;
	private boolean checkedAnswer4;
	private boolean checkedAnswer5;
	private double amount;
	
	public AnsweredQuestion(){}
	
	public AnsweredQuestion(User user, Question question, boolean checkedAnswer1, boolean checkedAnswer2, boolean checkedAnswer3, boolean checkedAnswer4, boolean checkedAnswer5, double amount) {
		this.setUser(user);
		this.setQuestion(question);
		this.setCheckedAnswer1(checkedAnswer1);
		this.setCheckedAnswer2(checkedAnswer2);
		this.setCheckedAnswer3(checkedAnswer3);
		this.setCheckedAnswer4(checkedAnswer4);
		this.setCheckedAnswer5(checkedAnswer5);
		this.setAmount(amount);
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	public boolean isCheckedAnswer1() {
		return checkedAnswer1;
	}
	public void setCheckedAnswer1(boolean checkedAnswer1) {
		this.checkedAnswer1 = checkedAnswer1;
	}
	public boolean isCheckedAnswer2() {
		return checkedAnswer2;
	}
	public void setCheckedAnswer2(boolean checkedAnswer2) {
		this.checkedAnswer2 = checkedAnswer2;
	}
	public boolean isCheckedAnswer3() {
		return checkedAnswer3;
	}
	public void setCheckedAnswer3(boolean checkedAnswer3) {
		this.checkedAnswer3 = checkedAnswer3;
	}
	public boolean isCheckedAnswer4() {
		return checkedAnswer4;
	}
	public void setCheckedAnswer4(boolean checkedAnswer4) {
		this.checkedAnswer4 = checkedAnswer4;
	}
	public boolean isCheckedAnswer5() {
		return checkedAnswer5;
	}
	public void setCheckedAnswer5(boolean checkedAnswer5) {
		this.checkedAnswer5 = checkedAnswer5;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
