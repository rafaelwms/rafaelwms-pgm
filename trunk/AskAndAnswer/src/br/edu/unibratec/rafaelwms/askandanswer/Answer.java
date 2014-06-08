package br.edu.unibratec.rafaelwms.askandanswer;

public class Answer {
	
	private int id_answer;
	private int question;
	private int number;
	private String text;
	private boolean correct;
	private double value;
	
	public Answer(){}
	
	public Answer(int id_answer, int number, String text, boolean correct, double value) {
		this.setId_answer(id_answer);
		this.setNumber(number);
		this.setText(text);
		this.setCorrect(correct);
		this.setValue(value);
	}
	
	public Answer(int id_answer, int question, int number, String text, boolean correct, double value) {
		this.setId_answer(id_answer);
		this.setQuestion(question);
		this.setNumber(number);
		this.setText(text);
		this.setCorrect(correct);
		this.setValue(value);
	}
	
	public Answer(int number, String text, boolean correct, double value,int question) {
		this.setQuestion(question);
		this.setNumber(number);
		this.setText(text);
		this.setCorrect(correct);
		this.setValue(value);
	}
	
	public int getQuestion() {
		return question;
	}
	public void setQuestion(int question) {
		this.question = question;
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
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

	public int getId_answer() {
		return id_answer;
	}

	public void setId_answer(int id_answer) {
		this.id_answer = id_answer;
	}

}
