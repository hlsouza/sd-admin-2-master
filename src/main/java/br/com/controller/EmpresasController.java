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

import br.com.model.dto.EmpresaDTO;
import br.com.service.EmpresaService;

@Controller
@RequestMapping("/empresas")
public class EmpresasController {

	@Autowired
	private EmpresaService service;

	@GetMapping
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("pages/empresa/empresas");
		mv.addObject("empresas", service.list());
		return mv;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView excluir(@PathVariable Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/empresas");
		this.service.remove(id);
		attributes.addFlashAttribute("removido", "Contato removido com sucesso!");
		return mv;
	}

	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		EmpresaDTO dto = this.service.getByCodigo(id);
		return novo(dto);
	}

	@GetMapping("/novo")
	public ModelAndView novo(EmpresaDTO dto) {
		ModelAndView mv = new ModelAndView("pages/empresa/nova_empresa");
		mv.addObject("empresa", dto);
		return mv;
	}

	@GetMapping("/ativar/{id}")
	public ModelAndView ativarDesativar(@PathVariable("id") Long id, RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/empresas");

		if (this.service.ativarDesativar(id)) {
			attributes.addFlashAttribute("ativadoDesativado", "Empresa ativada com sucesso!");
		} else {
			attributes.addFlashAttribute("ativadoDesativado", "Empresa desativada com sucesso!");
		}

		return mv;
	}

	@PostMapping("/save")
	public ModelAndView salvar(@Valid EmpresaDTO dto, BindingResult result, Model model,
			RedirectAttributes attributes) {
		ModelAndView mv = new ModelAndView("redirect:/empresas");

		if (result.hasErrors()) {
			return novo(dto);
		}

		attributes.addFlashAttribute("mensagem", "Empresa salva com sucesso");
		this.service.save(dto);
		return mv;
	}

}