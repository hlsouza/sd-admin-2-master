package br.com.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.model.dto.DependenteDTO;
import br.com.service.DependenteService;

@Controller
@RequestMapping("/dependentes")
public class DependenteController {

	@Autowired
	private DependenteService service;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("pages/dependente/dependentes");
		mv.addObject("dependentes", service.list());
		return mv;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/dependentes");
		this.service.remove(id);
		attributes.addFlashAttribute("removido", "Dependente removido com sucesso!");
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		DependenteDTO dto = this.service.getById(id);
		return novo(dto);
	}

	@GetMapping("/novo")
	public ModelAndView novo(DependenteDTO dto) {
		ModelAndView mv = new ModelAndView("pages/dependente/novo_dependente");
		mv.addObject("dependente", dto);
		return mv;
	}

	@GetMapping("/ativar/{id}")
	public ModelAndView ativarDesativar(@PathVariable("id") Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/dependentes");

		if (this.service.ativarDesativar(id)) {
			attributes.addFlashAttribute("ativadoDesativado", "Dependente ativado com sucesso!");
		} else {
			attributes.addFlashAttribute("ativadoDesativado", "Dependente desativado com sucesso!");
		}

		return mv;
	}

	@PostMapping("/save")
	public ModelAndView salvar(@Valid DependenteDTO dto, BindingResult result, Model model,
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/dependentes");

		if (result.hasErrors()) {
			return novo(dto);
		}

		attributes.addFlashAttribute("mensagem", "Dependente salvo com sucesso");
		this.service.save(dto);
		return mv;
	}

}