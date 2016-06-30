package com.cobranca.data.jpa.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cobranca.data.jpa.model.StatusTituto;
import com.cobranca.data.jpa.model.Titulo;
import com.cobranca.data.jpa.repository.TitulosRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TitulosRepository titulos;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(@Validated Titulo titulo, Errors erros) {		
		ModelAndView mv = new ModelAndView("CadastroTitulo");	
		
		if(erros.hasErrors()) {
			return mv;
		}
		
		titulos.save(titulo);			
		mv.addObject("mensagem", "Titulo salvo com sucesso!");
		return mv;
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		
		return mv;
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTituto> todosStatusTitulo() {
		return Arrays.asList(StatusTituto.values());
	}
			
}
