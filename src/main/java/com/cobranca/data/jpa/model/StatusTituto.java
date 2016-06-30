package com.cobranca.data.jpa.model;

public enum StatusTituto {

	PENDENTE("Pendente"),
	RECEBIDO("Recebido");
	
	private String descricao;
	
	StatusTituto(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
