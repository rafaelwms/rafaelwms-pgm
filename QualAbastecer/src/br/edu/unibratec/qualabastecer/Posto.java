package br.edu.unibratec.qualabastecer;

import java.io.Serializable;

public class Posto implements Serializable{
	
	private int id;
	private String nome;
	private double litroGasolina;
	private double litroEtanol;
	
	public Posto(){}
	
	public Posto(String nome){
		this.setNome(nome);
	}
	
	public Posto(int id, String nome){
		this.setId(id);
		this.setNome(nome);
	}
	
	public Posto(String nome, double gasolina, double etanol){
		this.setNome(nome);
		this.setLitroGasolina(gasolina);
		this.setLitroEtanol(etanol);
	}
	
	public Posto(int id, String nome, double gasolina, double etanol){
		this.setId(id);
		this.setNome(nome);
		this.setLitroGasolina(gasolina);
		this.setLitroEtanol(etanol);
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

	public double getLitroGasolina() {
		return litroGasolina;
	}

	public void setLitroGasolina(double litroGasolina) {
		this.litroGasolina = litroGasolina;
	}

	public double getLitroEtanol() {
		return litroEtanol;
	}

	public void setLitroEtanol(double litroEtanol) {
		this.litroEtanol = litroEtanol;
	}

}
