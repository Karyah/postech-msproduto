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

	public String getNome() {
		return nome;
	}

	public String getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getFabricante() {
		return fabricante;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

}
