package br.edu.rafaelwms.httpaula;

public class Equipe {

	String nome;
	String escudo;
	String site;

	public Equipe(String nome, String escudo, String site) {
		super();
		this.nome = nome;
		this.escudo = escudo;
		this.site = site;
	}

	@Override
	public String toString() {
		return nome;
	}

}