package br.com.fiap.msproduto.usecases;

import org.springframework.stereotype.Service;

import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.dto.ProdutoDTO;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;

@Service
public class EditarProdutoUseCase {

	private final IProdutoGateway produtoGateway;
	
	public EditarProdutoUseCase(IProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}
	
	public Produto editar(ProdutoDTO produtoDTO) {
		try {
			return produtoGateway.atualizarProduto(new Produto(
					produtoDTO.sku(),
					produtoDTO.nome(),
					produtoDTO.codigoDeBarras(),
					produtoDTO.preco(),
					produtoDTO.descricao(),
					produtoDTO.categoria(),
					produtoDTO.fabricante()
					)
				);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
