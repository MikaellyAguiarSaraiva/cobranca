package com.cobranca.data.jpa.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cobranca.data.jpa.model.StatusTituto;
import com.cobranca.data.jpa.model.Titulo;
import com.cobranca.data.jpa.repository.TitulosRepository;
import com.cobranca.data.jpa.service.TituloService;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CADASTRO_VIEW = "CadastroTitulo";
	
	@Autowired
	private TitulosRepository titulos;
	
	@Autowired
	private TituloService tituloService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo, Errors erros, RedirectAttributes attributes) {		
		if(erros.hasErrors()) {
			return CADASTRO_VIEW;
		}
		
		try {
			tituloService.salvar(titulo);			
			attributes.addFlashAttribute("mensagem", "Titulo salvo com sucesso!");		
			return "redirect:/titulos/novo";
		} catch (IllegalArgumentException e) {
			erros.rejectValue("dataVencimento", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@RequestMapping
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulos", todosTitulos);
		
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Titulo titulo) {		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(titulo);
		
		return mv;
	}
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		tituloService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Titulo excluido com sucesso!");
		return "redirect:/titulos";
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTituto> todosStatusTitulo() {
		return Arrays.asList(StatusTituto.values());
	}
			
}
