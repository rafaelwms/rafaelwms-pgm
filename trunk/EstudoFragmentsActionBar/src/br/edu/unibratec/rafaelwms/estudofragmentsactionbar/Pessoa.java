package br.edu.unibratec.rafaelwms.estudofragmentsactionbar;

import java.io.Serializable;

public class Pessoa implements Serializable{
	
	public String nome;
	public String sobrenome;
	
	
	public Pessoa(String nome, String sobrenome) {
		this.nome = nome;
		this.sobrenome = sobrenome;
	}


	@Override
	public String toString() {
		return nome;
	}
	
	

}
