package br.com.fiap.msproduto.gateway.database.jpa.entity;

import java.math.BigDecimal;
import br.com.fiap.msproduto.domain.Categoria;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="produto")
public class ProdutoEntity {

	@Id
	@Column(name="sku")
	private String sku;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="codigo_de_barras")
	private String codigoDeBarras;
	
	@Column(name="descricao")
	private String descricao;
	
	@Column(name="fabricante")
	private String fabricante;
	
	@Column(name="preco")
	private BigDecimal preco;
	
	@Column(name="categoria")
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;

	public ProdutoEntity() {
		super();
	}

	public ProdutoEntity(String sku, String nome, String codigoDeBarras, String descricao, String fabricante,
			BigDecimal preco, Categoria categoria) {
		super();
		this.sku = sku;
		this.nome = nome;
		this.codigoDeBarras = codigoDeBarras;
		this.descricao = descricao;
		this.fabricante = fabricante;
		this.preco = preco;
		this.categoria = categoria;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(String codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}
