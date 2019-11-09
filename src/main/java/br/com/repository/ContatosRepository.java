package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.model.Contato;

public interface ContatosRepository extends JpaRepository<Contato, Long> {

	
	public List<Contato> findByNomeContaining(String nome);
}
