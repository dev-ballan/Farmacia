package com.generation.farmacia.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table( name = "tb_produtos")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
	private Long id;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria; // OBJETO CATEGORIA PARA CRIAR A RELAÇÃO FK
	

	@NotBlank(message = "Nome do medicamento é obrigatório!")
	private String nome;
	
	@NotBlank(message = "O atributo Descricao é obrigatório!")
//	@Size(min = 10, max = 1000, message = "O atributo descricao deve conter no minimo 10 e no máximo 1000 caracteres!")
	private String descricao;
	
	private int quantidade;
	
	private String laboratorio;
	
	@Digits(integer = 3, fraction = 2, message = "Apenas centenas e 2 casas após o ponto.")
	private BigDecimal preco;
	
	private String foto;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}


	
	
}
