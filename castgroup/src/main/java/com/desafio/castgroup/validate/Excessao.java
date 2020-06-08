package com.desafio.castgroup.validate;

public class Excessao extends Exception {
	
	private static final long serialVersionUID = -7705825677170617641L;

	private String mensagem;
	private int codigo;

	public Excessao(String mensagem, int codigo) {
		super();
		this.mensagem = mensagem;
		this.codigo = codigo;
	}

	public Excessao() {
		super();

	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
