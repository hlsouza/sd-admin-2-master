package br.com.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_dependente")
@Getter
@Setter
@EqualsAndHashCode(of = {"id"})
public class Dependente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Nome é obrigatório")
	private String nome;
	
	//@NotBlank(message="Funcionário é obrigatório")
	//private Funcionario funcionario;
	
	private boolean ativo = true;	
	
}