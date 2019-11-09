package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Dependente;
import br.com.model.dto.DependenteDTO;
import br.com.repository.DependentesRepository;

@Service
public class DependenteService {

	@Autowired
	private DependentesRepository repository;
	
	@Transactional(readOnly=true)
	public List<Dependente> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(DependenteDTO dto) {
		this.repository.save(dto.converDTO(dto));
	}
	
	@Transactional
	public void remove(Long id) {
		//this.repository.delete(id);
	}
	
	@Transactional(readOnly=true)
	public DependenteDTO getById(Long id) {
		DependenteDTO dto = new DependenteDTO();
		//dto= dto.converterDependente(this.repository.findOne(id));
		return dto;
	}
	
	@Transactional
	public boolean ativarDesativar(Long id) {	
		boolean ativou = false;		
		Dependente dependente = this.repository.getOne(id);
		if(dependente.isAtivo()) {
			dependente.setAtivo(false);
			return ativou;
		}else {
			dependente.setAtivo(true);
			ativou = true;
		}
		return ativou;
	}
	
	public boolean ativaDesativarDependente(Dependente dependente) {
		if (dependente.isAtivo()) {
			ativaDesativaUsuario(dependente);
		} else {
			ativaDesativaUsuario(dependente);
		}
		return false;
	}


	
	@Transactional
	private void ativaDesativaUsuario(Dependente dependente) {

		if (dependente.isAtivo()) {
			dependente.setAtivo(false);
		} else {
			dependente.setAtivo(true);
		}

		this.repository.saveAndFlush(dependente);
	}

	
}