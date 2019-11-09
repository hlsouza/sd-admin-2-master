package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Dependente;

@Repository
public interface DependentesRepository extends JpaRepository<Dependente, Long> {
	
	public List<Dependente> findByNomeContaining(String nome);
}
