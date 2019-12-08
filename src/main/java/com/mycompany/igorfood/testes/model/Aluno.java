package com.mycompany.igorfood.testes.model;

public class Aluno {
	
	public Aluno() {
		
	}
	
	public Aluno(String nome, String ra, double n1, double n2) {
		this.nome = nome;
		this.ra = ra;
		this.nota1 = n1;
		this.nota2 = n2;
	}
	
	private String nome;
	private String ra;
	private double nota1;
	private double nota2;
	private double media;
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public double getNota1() {
		return nota1;
	}

	public void setNota1(double nota1) {
		this.nota1 = nota1;
	}

	public double getNota2() {
		return nota2;
	}

	public void setNota2(double nota2) {
		this.nota2 = nota2;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	@Override
	public String toString() {
		return "Aluno [nome=" + nome + ", ra=" + ra + ", nota1=" + nota1 + ", nota2=" + nota2 + ", media=" + media
				+ "]";
	}
	
}
