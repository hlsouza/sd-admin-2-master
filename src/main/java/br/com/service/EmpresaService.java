package br.com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Empresa;
import br.com.model.dto.EmpresaDTO;
import br.com.repository.EmpresasRepository;

@Service
public class EmpresaService {

	@Autowired
	private EmpresasRepository repository;
	
	@Transactional(readOnly=true)
	public List<Empresa> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public Empresa save(EmpresaDTO dto) {
		return this.repository.save(dto.converDTO(dto));
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);;
	}
	
	@Transactional(readOnly=true)
	public EmpresaDTO getByCodigo(Long codigo) {
		EmpresaDTO dto = new EmpresaDTO();
		dto= dto.converterEmpresa(this.repository.findById(codigo).get());
		return dto;
	}
	
	@Transactional
	public boolean ativarDesativar(Long id) {
		boolean ativou = false;
		
		Empresa Empresa = this.repository.getOne(id);
		if(Empresa.isAtivo()) {
			Empresa.setAtivo(false);
			return ativou;
		}else {
			Empresa.setAtivo(true);
			ativou = true;
		}
		return ativou;
	}
	
	public boolean ativaDesativarEmpresa(Empresa Empresa) {
		if (Empresa.isAtivo()) {
			ativaDesativaUsuario(Empresa);
		} else {
			ativaDesativaUsuario(Empresa);
		}
		return false;
	}
	
	@Transactional
	private void ativaDesativaUsuario(Empresa Empresa) {
		if (Empresa.isAtivo()) {
			Empresa.setAtivo(false);
		} else {
			Empresa.setAtivo(true);
		}
		this.repository.saveAndFlush(Empresa);
	}

}