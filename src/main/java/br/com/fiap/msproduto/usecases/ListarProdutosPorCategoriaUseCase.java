package br.com.fiap.msproduto.usecases;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.exception.NenhumProdutoCadastradoException;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;

@Service
public class ListarProdutosPorCategoriaUseCase {
	private final IProdutoGateway produtoGateway;
	
	public ListarProdutosPorCategoriaUseCase(IProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}
	
	public List<Produto> listar(Categoria categoria){
		try {
			List<Produto> produtos = produtoGateway.listarPorCategoria(categoria);

			if (Objects.isNull(produtos)) {
				throw new NenhumProdutoCadastradoException("Não existem produtos cadastrados com essa categoria");
			}
			
			return produtos;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
