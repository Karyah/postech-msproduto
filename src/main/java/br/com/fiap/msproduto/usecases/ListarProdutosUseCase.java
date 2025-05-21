package br.com.fiap.msproduto.usecases;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.exception.NenhumProdutoCadastradoException;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;

@Service
public class ListarProdutosUseCase {

	private final IProdutoGateway produtoGateway;
	
	public ListarProdutosUseCase(IProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}
	
	public List<Produto> listarTodos(){
		try {
			List<Produto> produtos = produtoGateway.listarTodos();
			
			if (Objects.isNull(produtos)) {
				throw new NenhumProdutoCadastradoException("Nenhum produto cadastrado.");
			}
			
			return produtos;
			
		} catch (Exception e) {
			return null;
		}
	}
}