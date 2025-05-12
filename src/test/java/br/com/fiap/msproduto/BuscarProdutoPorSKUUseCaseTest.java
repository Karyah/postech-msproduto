package br.com.fiap.msproduto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.exception.ProdutoNaoExisteException;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.usecases.BuscarProdutoPorSKUUseCase;

public class BuscarProdutoPorSKUUseCaseTest {
	
	private IProdutoGateway produtoGateway;
	private BuscarProdutoPorSKUUseCase buscarProdutoPorSKUUseCase;
	
	
	@BeforeEach
	void setUp() {
		this.produtoGateway = Mockito.mock(IProdutoGateway.class);
		this.buscarProdutoPorSKUUseCase = new BuscarProdutoPorSKUUseCase(produtoGateway);
	}
	
	@Test
	void deveRetornarProdutoQuandoSKUExiste() {
		  String sku = "ABC123";
	      Produto produtoMock = new Produto();
	      
	      produtoMock.setSku(sku);
	      
	      Mockito.when(produtoGateway.buscarProdutoPorSKU(sku)).thenReturn(produtoMock);
	      
	      Produto result = buscarProdutoPorSKUUseCase.buscar(sku);

	      assertNotNull(result);
	      assertEquals(sku, result.getSku());
	} 
	
	@Test
	void deveRetornarNullEmCasoDeErroInesperado() {
	    String sku = "ERRO";
	    Mockito.when(produtoGateway.buscarProdutoPorSKU(sku)).thenThrow(new RuntimeException("Erro inesperado"));

	    Produto result = buscarProdutoPorSKUUseCase.buscar(sku);

	    assertNull(result); 
	}
	
}
