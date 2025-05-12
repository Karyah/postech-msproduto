package br.com.fiap.msproduto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.usecases.ListarProdutosPorCategoriaUseCase;

public class ListarProdutosPorCategoriaUseCaseTest {
	
	private IProdutoGateway produtoGateway;
	private ListarProdutosPorCategoriaUseCase listarProdutosPorCategoriaUseCase;
	
	@BeforeEach
	void setUp() {
		this.produtoGateway = Mockito.mock(IProdutoGateway.class);
		this.listarProdutosPorCategoriaUseCase = new ListarProdutosPorCategoriaUseCase(produtoGateway);
	}
	
	@Test
	void deveListarProdutosPorCategoria() {
		Produto produto1 = new Produto();
		produto1.setCategoria(Categoria.ALIMENTO);
		
		Produto produto2 = new Produto();
		produto2.setCategoria(Categoria.ALIMENTO);
		
		Produto produto3 = new Produto();
		produto3.setCategoria(Categoria.BEBIDA);
		
		
		Mockito.when(produtoGateway.criarProduto(produto1)).thenReturn(produto1);
		Mockito.when(produtoGateway.criarProduto(produto2)).thenReturn(produto2);
		Mockito.when(produtoGateway.criarProduto(produto3)).thenReturn(produto3);
		
		List<Produto> produtosResult =  listarProdutosPorCategoriaUseCase.listar(Categoria.ALIMENTO);
		
		//Garantindo que a lista não é nula
		assertNotNull(produtosResult);
		
		boolean categoriaCorreta = produtosResult.stream().anyMatch(p -> p.getCategoria() == Categoria.ALIMENTO);
		
		//garantindo que existe a categoria correta
		assertThat(categoriaCorreta);
		
		//garantindo que ambos os registros foram retornados.
		assertThat(produtosResult.size() == 2);
	}	
}
