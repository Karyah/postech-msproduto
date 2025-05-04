package br.com.fiap.msproduto.usecases;

import org.springframework.stereotype.Service;

import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;

@Service
public class ExcluirProdutoUseCase {
	private final IProdutoGateway produtoGateway;
	
	public ExcluirProdutoUseCase(IProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}
	
	public void excluir(String sku) {
		try {
			produtoGateway.excluirProduto(sku);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
