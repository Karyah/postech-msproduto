package br.com.fiap.msproduto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.dto.ProdutoDTO;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.usecases.EditarProdutoUseCase;

public class EditarProdutoUseCaseTest {

	private IProdutoGateway produtoGateway;
	private EditarProdutoUseCase editarProdutoUseCase;
	
	@BeforeEach
	void setUp() {
		this.produtoGateway = Mockito.mock(IProdutoGateway.class);
		this.editarProdutoUseCase = new EditarProdutoUseCase(produtoGateway);
	}
	
	   @Test
	    void deveEditarProdutoComSucesso() {
	        ProdutoDTO dto = new ProdutoDTO(
	                "123", // sku
	                "Notebook", // nome
	                "98765432101234", // codigoDeBarras
	                "Notebook gamer", // descricao
	                "Dell", // fabricante
	                new BigDecimal("4999.99"), // preco
	                Categoria.ELETRONICO
	        );

	        Produto produtoAtualizado = new Produto(
	                dto.sku(),
	                dto.nome(),
	                dto.codigoDeBarras(),
	                dto.preco(),
	                dto.descricao(),
	                dto.categoria(),
	                dto.fabricante()
	        );

	        when(produtoGateway.atualizarProduto(any(Produto.class))).thenReturn(produtoAtualizado);

	        Produto resultado = editarProdutoUseCase.editar(dto);

	        assertNotNull(resultado);
	        assertEquals(dto.nome(), resultado.getNome());
	    }
}
