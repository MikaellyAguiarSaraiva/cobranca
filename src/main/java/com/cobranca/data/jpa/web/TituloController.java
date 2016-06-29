package com.cobranca.data.jpa.web;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cobranca.data.jpa.domain.StatusTituto;
import com.cobranca.data.jpa.domain.Titulo;
import com.cobranca.data.jpa.service.TitulosRepository;

@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	@Autowired
	private TitulosRepository titulos;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView("CadastroTitulo");
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView salvar(Titulo titulo) {		
		titulos.save(titulo);		
		ModelAndView mv = new ModelAndView("CadastroTitulo");		
		mv.addObject("mensagem", "Titulo salvo com sucesso!");
		return mv;
	}
	
	@RequestMapping
	public String pesquisar() {
		return "PesquisaTitulos";
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTituto> todosStatusTitulo() {
		return Arrays.asList(StatusTituto.values());
	}
			
}
