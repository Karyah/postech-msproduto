package br.com.fiap.msproduto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.usecases.ListarProdutosOrdenarPorMaiorPrecoUseCase;

public class ListarProdutosOrdenarPorMaiorPrecoUseCaseTest {

	private IProdutoGateway produtoGateway;
	private ListarProdutosOrdenarPorMaiorPrecoUseCase listarProdutosOrdenarPorMaiorPrecoUseCase;
	
	@BeforeEach
	void setUp() {
		this.produtoGateway = Mockito.mock(IProdutoGateway.class);
		this.listarProdutosOrdenarPorMaiorPrecoUseCase = new ListarProdutosOrdenarPorMaiorPrecoUseCase(produtoGateway);
	}

	@Test
	void deveListarProdutosOrdenarPorMaiorPreco() {
		Produto produto1 = new Produto();
		produto1.setPreco(new BigDecimal(10));
		produto1.setSku("produto1");
		
		Produto produto2 = new Produto();
		produto2.setPreco(new BigDecimal(20));
		produto2.setSku("produto2");
		
		Produto produto3 = new Produto();
		produto3.setPreco(new BigDecimal(30));
		produto3.setSku("produto3");
		
		List<Produto> produtosMock = List.of(produto3, produto2, produto1); 

		Mockito.when(produtoGateway.listarOrdenarPorMaiorPreco()).thenReturn(produtosMock);
		Mockito.when(produtoGateway.criarProduto(produto1)).thenReturn(produto1);
		Mockito.when(produtoGateway.criarProduto(produto2)).thenReturn(produto2);
		Mockito.when(produtoGateway.criarProduto(produto3)).thenReturn(produto3);
		
		List<Produto> produtos = listarProdutosOrdenarPorMaiorPrecoUseCase.listar();
		
		assertNotNull(produtos);
		assertFalse(produtos.isEmpty());
		assertTrue(produtos.size() == 3);
		
		Produto produtoMaiorPreco = produtos.get(0);
		Produto produtoMenorPreco = produtos.get(2);
		
		assertThat(produtoMaiorPreco.getPreco().compareTo(produtoMenorPreco.getPreco()) > 0 );
	}
}
