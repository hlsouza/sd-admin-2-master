package br.com.model.dto;

import br.com.model.Empresa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmpresaDTO {
	
	private Long codigo;

	private String nome;
	
	private String cnpj;
	
	public Empresa converDTO(EmpresaDTO dto) {
		Empresa empresa = new Empresa();
		empresa.setNome(dto.getNome());
		empresa.setCnpj(dto.getCnpj());
		empresa.setCodigo(dto.getCodigo());		
		return empresa;
	}
	
	public EmpresaDTO converterEmpresa(Empresa empresa) {
		
		EmpresaDTO dto = new EmpresaDTO();
		dto.setCnpj(empresa.getCnpj());
		dto.setNome(empresa.getNome());
		dto.setCodigo(empresa.getCodigo());		
		return dto;
	}
}