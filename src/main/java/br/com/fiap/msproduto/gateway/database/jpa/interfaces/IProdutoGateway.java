package br.com.fiap.msproduto.gateway.database.jpa.interfaces;

import java.util.List;
import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.domain.Produto;

public interface IProdutoGateway {
	
	List<Produto> listarTodos();
	
	List<Produto> listarOrdenarPorMenorPreco();
	
	List<Produto> listarOrdenarPorMaiorPreco();
	
	List<Produto> listarPorCategoria(Categoria categoria);
	
	Produto buscarProdutoPorSKU(String sku);
	
	Produto criarProduto(Produto produto);
	
	Produto atualizarProduto(Produto produto);
	
	void excluirProduto(String sku);
}

