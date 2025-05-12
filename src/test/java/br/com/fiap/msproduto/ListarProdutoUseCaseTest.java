package br.com.fiap.msproduto;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.usecases.ListarProdutosUseCase;

public class ListarProdutoUseCaseTest {
	
	private IProdutoGateway produtoGateway;
	private ListarProdutosUseCase listarProdutoUseCase;
	
	@BeforeEach
	void setUp() {
		this.produtoGateway = Mockito.mock(IProdutoGateway.class);
		this.listarProdutoUseCase = new ListarProdutosUseCase(produtoGateway);
	}
	
	@Test
	void deveListarProdutos() {
		Produto produto1 = new Produto();
		
		Produto produto2 = new Produto();
		
		Mockito.when(produtoGateway.criarProduto(produto1)).thenReturn(produto1);
		Mockito.when(produtoGateway.criarProduto(produto2)).thenReturn(produto2);
		
		List<Produto> produtosResult =  listarProdutoUseCase.listarTodos();

		assertNotNull(produtosResult);
		
	}	
}

