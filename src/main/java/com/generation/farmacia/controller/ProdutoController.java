package com.generation.farmacia.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Produto;
import com.generation.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository; // TRANSFERE A RESPONSABILIDADE DE CRIAÇÃO PARA O SPRING
	
//	@Autowired
//	private CategoriaRepository categoriaRepository;
	
	// BUSCA TODOS OS PRODUTOS DA LISTA
	@GetMapping
	public ResponseEntity<List<Produto>> getAll(){
		return ResponseEntity.ok(produtoRepository.findAll());
	}
	
	// BUSCA POR ID
	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id){
//		return produtoRepository.findById(id);
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	// BUSCA POR NOME
	@GetMapping("/nome{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
//		return produtoRepository.findById(id);
		return ResponseEntity.ok(produtoRepository.findByNomeContainingIgnoreCase(nome));
		
	}
	
	// BUSCA POR PREÇO MAIOR QUE ...
	@GetMapping("/precomaior/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMaior(@Valid @PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
	}
	
	// BUSCA POR PREÇO MENOR QUE ...
	@GetMapping("/precomenor/{preco}")
	public ResponseEntity<List<Produto>> getByPrecoMenor(@Valid @PathVariable BigDecimal preco){
		return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPreco(preco));
	}
	
	// BUSCA ENTRE PREÇOS
	@GetMapping("/preco/de{precoMin}a{precoMax}")
	public ResponseEntity<List<Produto>> getByPrecoBetween(@PathVariable BigDecimal precoMin, @PathVariable BigDecimal precoMax){
		return ResponseEntity.ok(produtoRepository.findAllByPrecoBetween(precoMin, precoMax));
	}
	
	//BUSCA POR NOME E LABORATÓRIO
	@GetMapping("/{nome}/{laboratorio}")
	public ResponseEntity<List<Produto>> getByNomeLab(@PathVariable String nome, @PathVariable String laboratorio){
		return ResponseEntity.ok(produtoRepository.findByNomeContainingAndLaboratorioContainingIgnoreCase(nome, laboratorio));
	}
	
	// CRIA OS PRODUTOS
	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto){
		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
	}
	
	// EDITA OS PRODUTOS
	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto){
		
		if (produto.getId()!= null) {
		return produtoRepository.findById(produto.getId())
				.map(resposta -> ResponseEntity.ok().body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.notFound().build());
	}
		return ResponseEntity.badRequest().build();
	}
	
	// DELETA PRODUTOS
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id){
		return produtoRepository.findById(id)
				.map(resposta -> {produtoRepository.deleteById(id);
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	
	
	
	
	
	}

