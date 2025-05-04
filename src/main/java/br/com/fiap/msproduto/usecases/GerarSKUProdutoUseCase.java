package br.com.fiap.msproduto.usecases;

import java.util.Random;

import org.springframework.stereotype.Service;

import br.com.fiap.msproduto.dto.ProdutoDTO;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;

@Service
public class GerarSKUProdutoUseCase {

	private final IProdutoGateway produtoGateway;
	
	public GerarSKUProdutoUseCase(IProdutoGateway produtoGateway) {
		this.produtoGateway = produtoGateway;
	}
	
	public String gerar(ProdutoDTO produtoDTO) {
		try {
			
			StringBuffer sb = new StringBuffer();
			
			// REGRA DE NEGÓCIO - Gerador de SKU:
				// Primeiros dois digitos do codigo de barras
				// Ultimo dígito do código de barra
				// Últimos dois caracteres de nome
				// Primeiros dois caracteres de categoria
				// Numero aleatorio de 100-999
			
			sb.append(produtoDTO.codigoDeBarras().charAt(0));
			sb.append(produtoDTO.codigoDeBarras().charAt(1));
			sb.append(produtoDTO.codigoDeBarras().charAt(produtoDTO.codigoDeBarras().length() - 1  ));
			sb.append(produtoDTO.nome().charAt(produtoDTO.nome().length() -2));
			sb.append(produtoDTO.nome().charAt(produtoDTO.nome().length() -1 ));
			sb.append(produtoDTO.categoria().name().charAt(0));
			sb.append(produtoDTO.categoria().name().charAt(1));
			
			Random random = new Random();
			
			sb.append(10 + random.nextInt(900));
			
			return sb.toString();
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
