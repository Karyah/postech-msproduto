package br.com.fiap.msproduto.usecases;

import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Service;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.exception.NenhumProdutoCadastradoException;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;

@Service
public class ListarProdutosOrdenarPorMaiorPrecoUseCase {
	private final IProdutoGateway produtoGateway;
	
	public ListarProdutosOrdenarPorMaiorPrecoUseCase(IProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}
	
	public List<Produto> listar(){
		try {
			List<Produto> produtos =  produtoGateway.listarOrdenarPorMaiorPreco();
			
			if (Objects.isNull(produtos)) {
				throw new NenhumProdutoCadastradoException("Não existem produtos cadastrados.");
			}
			
			return produtos;
		} catch (Exception e) {
			return null;
		}
	}
}
