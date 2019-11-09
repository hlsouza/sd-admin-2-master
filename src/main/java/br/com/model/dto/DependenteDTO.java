package br.com.model.dto;

import br.com.model.Dependente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DependenteDTO {
	
	private Long id;

	private String nome;
	
	private String cnpj;
	
	public Dependente converDTO(DependenteDTO dto) {
		Dependente dependente = new Dependente();
		dependente.setId(dto.getId());
		dependente.setNome(dto.getNome());
		//dependente.setFuncionario(dto.getFuncionario());		
		return dependente;
	}
	
	public DependenteDTO converterDependente(Dependente dependente) {
		
		DependenteDTO dto = new DependenteDTO();
		dto.setId(dependente.getId());
		dto.setNome(dependente.getNome());
		//dto.setFuncionario(dependente.getFuncionario());		
		return dto;
	}
}