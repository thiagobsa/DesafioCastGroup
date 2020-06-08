package com.desafio.castgroup.models;

import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Dado {
	
	@JsonIgnore
	private Long id;

	private String dado;

	public Dado(Long id, String dado) {
		super();
		this.id = id;
		this.dado = dado;
	}

	public Dado() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDado() {
		return dado;
	}

	public void setDado(String dado) {
		this.dado = dado;
	}
	
	



}
