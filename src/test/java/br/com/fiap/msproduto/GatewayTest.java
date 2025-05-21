package br.com.fiap.msproduto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.gateway.database.jpa.ProdutoGateway;
import br.com.fiap.msproduto.gateway.database.jpa.entity.ProdutoEntity;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.gateway.database.jpa.repository.ProdutoRepository;

public class GatewayTest {

	private ProdutoRepository produtoRepository;
	private ProdutoGateway produtoGateway;
	
	@BeforeEach
	void setUp() {
		this.produtoRepository = Mockito.mock(ProdutoRepository.class);
		this.produtoGateway = new ProdutoGateway(produtoRepository);
	}
	
	
	@Test
	void domainParaEntity(){
		Produto produto = new Produto(
				"sku", 
				"nome", 
				"codigoBarras",
				new BigDecimal(5000),
				"Descricao",
				Categoria.ALIMENTO,
				"Fabricante");
		
		ProdutoEntity produtoEntity = produtoGateway.domainParaEntity(produto);
		
		assertThat(produtoEntity.getSku().equals(produto.getSku()));
		assertThat(produtoEntity.getNome().equals(produto.getNome()));
		assertThat(produtoEntity.getCodigoDeBarras().equals(produto.getCodigoDeBarras()));
		assertThat(produtoEntity.getDescricao().equals(produto.getDescricao()));
		assertThat(produtoEntity.getCategoria().equals(produto.getCategoria()));
		assertThat(produtoEntity.getFabricante().equals(produto.getFabricante()));
		assertThat(produtoEntity.getPreco().compareTo(produto.getPreco()) == 0);
	}
	
	
	@Test
	void testaEntityParaDomain() {
		ProdutoEntity produtoEntity = new ProdutoEntity(
				"sku",
				"nome",
				"codigoBarras",
				"descricao",
				"fabricante",
				new BigDecimal(900),
				Categoria.HIGIENE_PESSOAL);
		
		Produto produto = produtoGateway.entityParaDomain(produtoEntity);
		
		assertThat(produtoEntity.getSku().equals(produto.getSku()));
		assertThat(produtoEntity.getNome().equals(produto.getNome()));
		assertThat(produtoEntity.getCodigoDeBarras().equals(produto.getCodigoDeBarras()));
		assertThat(produtoEntity.getDescricao().equals(produto.getDescricao()));
		assertThat(produtoEntity.getCategoria().equals(produto.getCategoria()));
		assertThat(produtoEntity.getFabricante().equals(produto.getFabricante()));
		assertThat(produtoEntity.getPreco().compareTo(produto.getPreco()) == 0);
	}
}
