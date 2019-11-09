package br.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.model.Empresa;

@Repository
public interface EmpresasRepository extends JpaRepository<Empresa, Long> {
	
	public List<Empresa> findByNomeContaining(String nome);
}
