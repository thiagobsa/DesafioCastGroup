package com.desafio.castgroup.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErroRetorno {
	
	@JsonProperty("mensagem")
	private String mensagem;

	@JsonProperty("codigoErro")
	private Integer codigoErro;

	public String getMessage() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Integer getCodigoErro() {
		return codigoErro;
	}

	public void setCodigoErro(Integer codigoErro) {
		this.codigoErro = codigoErro;
	}

}
