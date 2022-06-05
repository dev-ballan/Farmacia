package com.generation.farmacia.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public List <Produto> findByNomeContainingIgnoreCase(@Param ("nome")String nome);
	public List <Produto> findByPrecoGreaterThanOrderByPreco(@Param ("preco") BigDecimal preco);
	public List <Produto> findByPrecoLessThanOrderByPreco(@Param ("preco") BigDecimal preco);
	public List <Produto> findAllByPrecoBetween(@Param ("precoMin") BigDecimal precoMin, @Param ("precoMax") BigDecimal precoMax);
	public List <Produto> findByNomeContainingAndLaboratorioContainingIgnoreCase(@Param ("nome") String nome, @Param ("laboratorio") String laboratorio);

}
