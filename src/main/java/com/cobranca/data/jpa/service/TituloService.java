package com.cobranca.data.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.cobranca.data.jpa.model.StatusTituto;
import com.cobranca.data.jpa.model.Titulo;
import com.cobranca.data.jpa.repository.TituloFilter;
import com.cobranca.data.jpa.repository.TitulosRepository;

@Service
public class TituloService {
	
	@Autowired
	private TitulosRepository titulos;
	
	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
		
	}
	
	public void excluir(Long codigo) {
		try {
			titulos.delete(codigo);
		} catch (Exception e) {
			throw new IllegalArgumentException("Erro na exclusão do titulo");
		}
	}

	public String receber(Long codigo) {
		Titulo titulo = titulos.findOne(codigo);
		titulo.setStatus(StatusTituto.RECEBIDO);
		titulos.save(titulo);
		
		return StatusTituto.RECEBIDO.getDescricao();
	}
	
	public List<Titulo> filtrar(TituloFilter filtro) {
		String descricao = filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		return titulos.findByDescricaoContaining(descricao);		
	}
	

}
