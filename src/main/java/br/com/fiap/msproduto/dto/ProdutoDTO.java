package br.com.fiap.msproduto.dto;

import java.math.BigDecimal;

import br.com.fiap.msproduto.domain.Categoria;

public record ProdutoDTO(
		String sku, 
		String nome,
		String codigoDeBarras,
		String descricao, 
		String fabricante,
		BigDecimal preco, 
		Categoria categoria) {

}
