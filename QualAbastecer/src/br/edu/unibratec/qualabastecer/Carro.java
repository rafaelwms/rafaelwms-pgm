package br.edu.unibratec.qualabastecer;
import java.io.Serializable;



public class Carro implements Serializable{
	
	private int id;
	private String nome;
	
	public Carro(){}
	
	public Carro(String nome){
		this.setNome(nome);
	}
	
	public Carro(int id, String nome){
		this.setId(id);
		this.setNome(nome);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	

}
