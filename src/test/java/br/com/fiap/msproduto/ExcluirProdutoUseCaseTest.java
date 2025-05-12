package br.com.fiap.msproduto;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.usecases.ExcluirProdutoUseCase;

public class ExcluirProdutoUseCaseTest {
	private IProdutoGateway produtoGateway;
	private ExcluirProdutoUseCase excluirProdutoUseCase;
	
	@BeforeEach
	void setUp() {
		this.produtoGateway = Mockito.mock(IProdutoGateway.class);
		this.excluirProdutoUseCase = new ExcluirProdutoUseCase(produtoGateway);
	}
	
	@Test
	void deveExcluirProduto() {
		String sku = "sku";
		excluirProdutoUseCase.excluir(sku);
		
	    verify(produtoGateway, times(1)).excluirProduto(sku);
	}
}
