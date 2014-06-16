package br.edu.unibratec.qualabastecer;

import java.io.Serializable;

public class Posto implements Serializable{
	
	private int id;
	private String nome;
	private int atendimento;
	private double litroGasolina;
	private double litroEtanol;
	private double litroDiesel;
	
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
	
	public Posto(String nome, int atendimento, double gasolina, double etanol, double diesel){
		this.setNome(nome);
		this.setAtendimento(atendimento);
		this.setLitroGasolina(gasolina);
		this.setLitroEtanol(etanol);
		this.setLitroDiesel(diesel);
	}
	
	public Posto(int id, String nome, int atendimento, double gasolina, double etanol, double diesel){
		this.setId(id);
		this.setNome(nome);
		this.setAtendimento(atendimento);
		this.setLitroGasolina(gasolina);
		this.setLitroEtanol(etanol);
		this.setLitroDiesel(diesel);
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

	public int getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(int atendimento) {
		this.atendimento = atendimento;
	}

	public double getLitroDiesel() {
		return litroDiesel;
	}

	public void setLitroDiesel(double litroDiesel) {
		this.litroDiesel = litroDiesel;
	}

}
