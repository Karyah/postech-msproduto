package br.com.fiap.msproduto.domain;

import java.math.BigDecimal;
import br.com.fiap.msproduto.exception.CategoriaInvalidaException;
import br.com.fiap.msproduto.exception.NomeInvalidoException;
import br.com.fiap.msproduto.exception.PrecoInvalidoException;
import br.com.fiap.msproduto.exception.SkuInvalidoException;

public class Produto {
	
	private String sku;
	private String nome;
	private String descricao; 
	private String fabricante; 
	private BigDecimal preco; 
	private Categoria categoria; 
	
	private void validaNome(String nome)  throws NomeInvalidoException{
		if (nome.equals("") || nome == null) {
			throw new NomeInvalidoException("Nome deve ser informado.");
		}
	}
	
	private void validaSku(String sku) throws SkuInvalidoException{
		if (sku.contentEquals("") || sku == null) {
			throw new SkuInvalidoException("Sku deve ser informado.");
		}
	}
	
	private void validaPreco(BigDecimal preco) throws PrecoInvalidoException{
		if (preco.equals(null) || preco == null) {
			throw new PrecoInvalidoException("Preço deve ser informado.");
		}
		if (preco.equals(BigDecimal.ZERO) || preco.compareTo(BigDecimal.ZERO) == -1 ) {
			throw new PrecoInvalidoException("Preço inválido.");
		}
	}
	
	private void validaCategoria(Categoria categoria) throws CategoriaInvalidaException{
		if (categoria == null) {
			throw new CategoriaInvalidaException("Categoria deve ser escolhida.");
		}
	}
	
	public Produto() {
		super();
	}

	public Produto(String sku, String nome, BigDecimal preco, String descricao, Categoria categoria,
			String fabricante){
		
		try {
			validaSku(sku);
			validaNome(nome);
			validaPreco(preco);
			validaCategoria(categoria);
		} catch (SkuInvalidoException | NomeInvalidoException | PrecoInvalidoException | CategoriaInvalidaException e) {
			e.printStackTrace();
		}

		this.sku = sku;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
		this.categoria = categoria;
		this.fabricante = fabricante;
	}

	public String getSku() {
		return sku;
	}
	
	public void setSku(String sku) {
		try {
			validaSku(sku);
		} catch (SkuInvalidoException e) {
			e.printStackTrace();
		}
		this.sku = sku;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		try {
			validaNome(nome);
		} catch (NomeInvalidoException e) {
			e.printStackTrace();
		}
		this.nome = nome;
	}
	
	public BigDecimal getPreco() {
		return preco;
	}
	
	public void setPreco(BigDecimal preco) {
		try {
			validaPreco(preco);
		} catch (PrecoInvalidoException e) {
			e.printStackTrace();
		}
		this.preco = preco;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		try {
			validaCategoria(categoria);
		} catch (CategoriaInvalidaException e) {
			e.printStackTrace();
		}
		this.categoria = categoria;
	}
	
	public String getFabricante() {
		return fabricante;
	}
	
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}		
}
