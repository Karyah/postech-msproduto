	package br.com.fiap.msproduto;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.dto.ProdutoDTO;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.usecases.CriarProdutoUseCase;
import br.com.fiap.msproduto.usecases.GerarSKUProdutoUseCase;

public class CriarProdutoUseCaseTest {
	
	private IProdutoGateway produtoGateway;
	private CriarProdutoUseCase criarProdutoUseCase;
	private GerarSKUProdutoUseCase gerarSKUProdutoUseCase;
	
	@BeforeEach
	void setUp() {
		this.produtoGateway = Mockito.mock(IProdutoGateway.class);
		this.gerarSKUProdutoUseCase = new GerarSKUProdutoUseCase(produtoGateway);
		this.criarProdutoUseCase = new CriarProdutoUseCase(produtoGateway, gerarSKUProdutoUseCase);
	}
	
	@Test
	void deveCriarProdutoQuandoRecebeDadosValidos() {
		ProdutoDTO produtoDTOMock = new ProdutoDTO(
				"",			  	  //sku
				"Televisão",	  //nome
				"12345678901234", //codigoDeBarras
				"Descrição",	  //descricao
				"", 			  //fabricante
				new BigDecimal("1033"),
				Categoria.ELETRONICO
		);
		Produto produto = dtoParaDomain(produtoDTOMock);
		
		Mockito.when(produtoGateway.criarProduto(Mockito.any()))
	       .thenReturn(produto);
		
		Produto produtoResult = criarProdutoUseCase.criar(produtoDTOMock);

		assertNotNull(produtoResult);
	}
	
	@Test
	void naoDeveCriarProdutoQuandoNomeInvalido() {
		ProdutoDTO produtoDTOMock = new ProdutoDTO(
				"",			  //sku
				null,	  //nome
				"12345678901234", //codigoDeBarras
				"Descrição",	  //descricao
				"", 			  //fabricante
				new BigDecimal("1033"),
				Categoria.ELETRONICO
		);
		
		Produto produto = dtoParaDomain(produtoDTOMock);
		
		Mockito.when(produtoGateway.criarProduto(Mockito.any()))
	       .thenReturn(produto);
		
		Produto produtoResult = criarProdutoUseCase.criar(produtoDTOMock);
		
		assertNull(produtoResult);
		
	}
	
	@Test
	void naoDeveCriarProdutoQuandoPrecoInvalido() {
		ProdutoDTO produtoDTOMock = new ProdutoDTO(
				"",			  	  //sku
				"Televisão",	  //nome
				"12345678901234", //codigoDeBarras
				"Descrição",	  //descricao
				"", 			  //fabricante
				null,
				Categoria.ELETRONICO
		);
		
		Produto produto = dtoParaDomain(produtoDTOMock);
		
		Mockito.when(produtoGateway.criarProduto(Mockito.any()))
	       .thenReturn(produto);
		
		Produto produtoResult = criarProdutoUseCase.criar(produtoDTOMock);
		
		assertNull(produtoResult);
	}
	
	@Test
	void naoDeveCriarProdutoQuandoCategoriaInvalida() {
		ProdutoDTO produtoDTOMock = new ProdutoDTO(
				"",			  	  //sku
				"Televisão",	  //nome
				"12345678901234", //codigoDeBarras
				"Descrição",	  //descricao
				"", 			  //fabricante
				new BigDecimal("1033"),
				null
		);
		Produto produto = dtoParaDomain(produtoDTOMock);
		
		Mockito.when(produtoGateway.criarProduto(Mockito.any()))
	       .thenReturn(produto);
		
		Produto produtoResult = criarProdutoUseCase.criar(produtoDTOMock);
		
		assertNull(produtoResult);
	}
	
	@Test
	void naoDeveCriarProdutoQuandoCodigoDeBarrasInvalido() {
		ProdutoDTO produtoDTOMock = new ProdutoDTO(
				"",			  	//sku
				"Televisão",	//nome
				null, 			//codigoDeBarras
				"Descrição",	//descricao
				"", 			//fabricante
				new BigDecimal("1033"),
				Categoria.ELETRONICO
		);
		Produto produto = dtoParaDomain(produtoDTOMock);
		
		Mockito.when(produtoGateway.criarProduto(Mockito.any()))
	       .thenReturn(produto);
		
		Produto produtoResult = criarProdutoUseCase.criar(produtoDTOMock);
		
		assertNull(produtoResult);
	}
	
	Produto dtoParaDomain(ProdutoDTO produtoDTO) {
		return new Produto(
				produtoDTO.sku(),
				produtoDTO.nome(),
				produtoDTO.codigoDeBarras(),
				produtoDTO.preco(), 
				produtoDTO.descricao(),
				produtoDTO.categoria(),
				produtoDTO.fabricante());
	}
}
