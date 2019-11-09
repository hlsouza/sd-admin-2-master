package br.com.resource;

import java.util.List;
import java.util.Optional;

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

import br.com.model.Contato;
import br.com.service.ContatoService;

@RestController
@RequestMapping("/api")
public class ContatoResource {
	
	private ContatoService service;
	
	@Autowired
	public ContatoResource(ContatoService service) {
		this.service = service;
	}
	
	@PostMapping("/contatos")
	public ResponseEntity<Contato> save(@RequestBody Contato contato) {
		this.service.save(contato);
		return new ResponseEntity<Contato>(contato, HttpStatus.CREATED);
	}
	
	@GetMapping("/contatos")
	public ResponseEntity<List<Contato>> list() {
		return ResponseEntity.ok(this.service.list());
	}
	
	@DeleteMapping("/contatos/{id}") 
	public ResponseEntity<Contato> removeById(@PathVariable Long id) {
		Optional<Contato> contato = this.service.getById(id);
		if (contato.isPresent()) {
			this.service.remove(contato.get().getId());
			return ResponseEntity.ok().body(contato.get());
		} else {
			return ResponseEntity.noContent().build();
		}
	}
	
	@PutMapping("/contatos")
	public ResponseEntity<Contato> update(@RequestBody Contato contato) {
		if (contato != null) {
			Contato contatoPersisted = this.service.getById(contato.getId()).get();
			contatoPersisted.setNome(contato.getNome());
			contatoPersisted.setEmail(contato.getEmail());
			this.service.save(contatoPersisted);
			return ResponseEntity.ok().body(contatoPersisted);
		}
		return ResponseEntity.noContent().build();
	}
		
}
