package br.com.fiap.msproduto.usecases;

import org.springframework.stereotype.Service;

import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.dto.ProdutoDTO;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;

@Service
public class CriarProdutoUseCase {

	private final IProdutoGateway produtoGateway;
	
	private final GerarSKUProdutoUseCase gerarSKUProduto;
	
	public CriarProdutoUseCase(IProdutoGateway produtoGateway, GerarSKUProdutoUseCase gerarSKUProduto) {
		this.produtoGateway = produtoGateway;
		this.gerarSKUProduto = gerarSKUProduto;
	}
	
	public Produto criar(ProdutoDTO produtoDTO) {
		try {
			String sku = gerarSKUProduto.gerar(produtoDTO);
			
			return produtoGateway.criarProduto(new Produto(
					sku,
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
