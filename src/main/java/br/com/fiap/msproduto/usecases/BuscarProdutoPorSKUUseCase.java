package br.com.fiap.msproduto.usecases;


import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.exception.ProdutoNaoExisteException;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;

@Service
public class BuscarProdutoPorSKUUseCase {

	private final IProdutoGateway produtoGateway;
	
	public BuscarProdutoPorSKUUseCase(IProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}
	
	public Produto buscar(String sku) {
		try {
			Produto produto = produtoGateway.buscarProdutoPorSKU(sku);
			
			if (Objects.isNull(produto)) {
				throw new ProdutoNaoExisteException("Não existe produto com essa SKU.");
			}
			return produto;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

} 
