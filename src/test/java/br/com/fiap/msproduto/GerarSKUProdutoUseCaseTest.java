package br.com.fiap.msproduto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.dto.ProdutoDTO;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.usecases.GerarSKUProdutoUseCase;

public class GerarSKUProdutoUseCaseTest {

	private IProdutoGateway produtoGateway;
	private GerarSKUProdutoUseCase gerarSKUProdutoUseCase;
	
	@BeforeEach
	void setUp() {
		this.produtoGateway = Mockito.mock(IProdutoGateway.class);
		this.gerarSKUProdutoUseCase = new GerarSKUProdutoUseCase(produtoGateway);
	}
	
	@Test
	void deveGerarSKUCorretamente() {
		// REGRA DE NEGÓCIO - Gerador de SKU:
		// Primeiros dois digitos do codigo de barras
		// Ultimo dígito do código de barra
		// Últimos dois caracteres de nome
		// Primeiros dois caracteres de categoria
		// Numero aleatorio de 100-999
		
		String codigoDeBarras = "12345678901234";
		String nome = "Miojo";
		Categoria categoria = Categoria.ALIMENTO;

		String inicioSkuQueDeveSerGerada = "124joAL";
		
		ProdutoDTO produtoDTOMock = new ProdutoDTO(
				null, 
				nome, 
				codigoDeBarras,
				null, 
				null,
				null, 
				categoria);
		
		Produto produtoMock = new Produto();
		
		produtoMock.setCategoria(categoria);
		produtoMock.setNome(nome);
		produtoMock.setCodigoDeBarras(codigoDeBarras);
		
		Mockito.when(produtoGateway.criarProduto(produtoMock))
	       .thenReturn(produtoMock);
		
		String skuGerada = gerarSKUProdutoUseCase.gerar(produtoDTOMock);
		
		// Apenas o inicio do sku deve ser validado já que os últimos 3 números
		// são aleatórios.
		String inicioSkuGerada = skuGerada.substring(0, 7);
		
		assertEquals(inicioSkuQueDeveSerGerada, inicioSkuGerada);
		
		String fimSkuGerado = skuGerada.substring(7, 9);
		
		assertNotNull(fimSkuGerado);
	}
}
