/**
 * 
 */
package br.com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.model.Contato;
import br.com.model.dto.ContatoPesquisaDTO;
import br.com.repository.ContatosRepository;

@Service
public class ContatoService {

	@Autowired
	private ContatosRepository repository;
	
	@Transactional(readOnly=true)
	public List<Contato> list(){
		return this.repository.findAll();
	}
	
	@Transactional
	public void save(Contato contato) {
		this.repository.save(contato);
	}
	
	@Transactional
	public void remove(Long id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<Contato> getById(Long id) {
		return this.repository.findById(id);
	}
	
	public List<Contato> filtrar(ContatoPesquisaDTO contato) {
		String nome = contato.getNome() == null ? "%" : contato.getNome()+"%";
		return repository.findByNomeContaining(nome);
	}

	@Transactional
	public boolean ativarDesativar(Long id) {
				
		boolean ativou = false;
		
		Contato contato = this.repository.getOne(id);
		if(contato.isAtivo()) {
			contato.setAtivo(false);
			return ativou;
		}else {
			contato.setAtivo(true);
			ativou = true;
		}
		return ativou;
	}
	
	public boolean ativaDesativarContato(Contato contato) {
		if (contato.isAtivo()) {
			ativaDesativaUsuario(contato);
		} else {
			ativaDesativaUsuario(contato);
		}
		return false;
	}
	
	@Transactional
	private void ativaDesativaUsuario(Contato contato) {

		if (contato.isAtivo()) {
			contato.setAtivo(false);
		} else {
			contato.setAtivo(true);
		}

		this.repository.saveAndFlush(contato);
	}

}
