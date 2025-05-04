package br.com.fiap.msproduto.gateway.database.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.gateway.database.jpa.entity.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, String>{
	
	List<ProdutoEntity> findByCategoria(Categoria categoria);
	
	List<ProdutoEntity> findAllByOrderByPreco();
	
	List<ProdutoEntity> findAllByOrderByPrecoDesc();
	
}





