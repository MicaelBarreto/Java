package br.edu.ifcvideira.Trabalho;

public class Produto {
	
	private String nomeProduto;
	private int codProduto;
	private int qnt;
	private double valor;
	private String registroMS;
	
	public String getNomeProduto() {
		return nomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}
	public int getCodProduto() {
		return codProduto;
	}
	public void setCodProduto(int codProduto) {
		this.codProduto = codProduto;
	}
	public int getQnt() {
		return qnt;
	}
	public void setQnt(int qnt) {
		this.qnt = qnt;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getRegistroMS() {
		return registroMS;
	}
	public void setRegistroMS(String registroMS) {
		this.registroMS = registroMS;
	}
	
}
