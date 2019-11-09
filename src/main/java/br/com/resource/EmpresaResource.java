package br.com.resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.model.Empresa;
import br.com.model.dto.EmpresaDTO;
import br.com.service.EmpresaService;

@RestController
@RequestMapping("/api")
public class EmpresaResource {
	
	private EmpresaService service;
	
	@Autowired
	public EmpresaResource(EmpresaService service) {
		this.service = service;
	}
	
	@PostMapping("/empresas")
	public ResponseEntity<Empresa> save(@RequestBody EmpresaDTO empresaDTO) {
		Empresa empresa = this.service.save(empresaDTO);
		empresa.add(linkTo(methodOn(EmpresaResource.class).removeById(empresa.getCodigo())).withRel("Deleta Empresa"));
		empresa.add(linkTo(methodOn(EmpresaResource.class).getById(empresa.getCodigo())).withRel("Recupera Empresa"));
		empresa.add(linkTo(methodOn(EmpresaResource.class).update(empresa)).withRel("Edita Empresa"));			
		return new ResponseEntity<Empresa>(empresa, HttpStatus.CREATED);
	}
		
	@GetMapping("/empresas")
	public ResponseEntity<List<Empresa>> list(){
		List<Empresa> empresas = this.service.list();
		for(Empresa empresa : empresas) {
			empresa.add(linkTo(methodOn(EmpresaResource.class).removeById(empresa.getCodigo())).withRel("Deleta Empresa"));
			empresa.add(linkTo(methodOn(EmpresaResource.class).getById(empresa.getCodigo())).withRel("Recupera Empresa"));
			empresa.add(linkTo(methodOn(EmpresaResource.class).update(empresa)).withRel("Edita Empresa"));			
		}
		return ResponseEntity.ok(empresas);
	}
	
	@GetMapping("/empresas/{codigo}") 
	public ResponseEntity<Empresa> getById(@PathVariable Long codigo) {
		EmpresaDTO empresaDTO = this.service.getByCodigo(codigo);
		if (empresaDTO != null) {
			this.service.getByCodigo(empresaDTO.getCodigo());
			return ResponseEntity.ok().body(empresaDTO.converDTO(empresaDTO));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@DeleteMapping("/empresas/{codigo}") 
	public ResponseEntity<Empresa> removeById(@PathVariable Long codigo) {
		EmpresaDTO empresaDTO = this.service.getByCodigo(codigo);
		if (empresaDTO != null) {
			this.service.remove(empresaDTO.getCodigo());
			return ResponseEntity.ok().body(empresaDTO.converDTO(empresaDTO));
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping("/empresas")
	public ResponseEntity<Empresa> update(@RequestBody Empresa empresa) {
		if (empresa != null) {
			EmpresaDTO empresaDTO = this.service.getByCodigo(empresa.getCodigo());
			empresaDTO.setNome(empresa.getNome());
			empresaDTO.setCnpj(empresa.getCnpj());
			empresa = this.service.save(empresaDTO);
			return ResponseEntity.ok().body(empresa);
		}
		return ResponseEntity.noContent().build();
	}
		
}
