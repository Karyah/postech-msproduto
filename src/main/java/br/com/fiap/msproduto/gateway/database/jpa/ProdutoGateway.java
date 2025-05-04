package br.com.fiap.msproduto.gateway.database.jpa;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.gateway.database.jpa.entity.ProdutoEntity;
import br.com.fiap.msproduto.gateway.database.jpa.interfaces.IProdutoGateway;
import br.com.fiap.msproduto.gateway.database.jpa.repository.ProdutoRepository;

@Service
public class ProdutoGateway implements IProdutoGateway{

	private final ProdutoRepository produtoRepository;
	
	public ProdutoGateway(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public List<Produto> listarTodos() {
		List<Produto> produtos = new ArrayList<Produto>();
		List<ProdutoEntity> produtoEntities = produtoRepository.findAll();
		
		for (ProdutoEntity produtoEntity : produtoEntities) {
			produtos.add(entityParaDomain(produtoEntity));
		}
		
		return produtos;
	}

	@Override
	public List<Produto> listarOrdenarPorMenorPreco() {
		List<Produto> produtos = new ArrayList<Produto>();
		List<ProdutoEntity> produtoEntities = produtoRepository.findAllByOrderByPreco();
		
		for (ProdutoEntity produtoEntity : produtoEntities) {
			produtos.add(entityParaDomain(produtoEntity));
		}
		
		return produtos;
	}

	@Override
	public List<Produto> listarOrdenarPorMaiorPreco() {
		List<Produto> produtos = new ArrayList<Produto>();
		List<ProdutoEntity> produtoEntities = produtoRepository.findAllByOrderByPrecoDesc();
		
		for (ProdutoEntity produtoEntity : produtoEntities) {
			produtos.add(entityParaDomain(produtoEntity));
		}
		
		return produtos;
	}
	
	@Override
	public List<Produto> listarPorCategoria(Categoria categoria) {
		List<Produto> produtos = new ArrayList<Produto>();
		List<ProdutoEntity> produtoEntities = produtoRepository.findByCategoria(categoria);
		
		for (ProdutoEntity produtoEntity : produtoEntities) {
			produtos.add(entityParaDomain(produtoEntity));
		}
		
		return produtos;
	}

	@Override
	public Produto buscarProdutoPorSKU(String sku) throws NoSuchElementException{
		Optional<ProdutoEntity> optProdutoEntity = produtoRepository.findById(sku);
		Produto produto = entityParaDomain(optProdutoEntity.get());
		return produto;
	}
	
	@Override
	public Produto criarProduto(Produto produto) {
		ProdutoEntity produtoEntity = produtoRepository.save(domainParaEntity(produto));
		return entityParaDomain(produtoEntity);
		
	}

	@Override
	public Produto atualizarProduto(Produto produto) {
		ProdutoEntity produtoEntity = produtoRepository.save(domainParaEntity(produto));
		return entityParaDomain(produtoEntity);
	}

	@Override
	public void excluirProduto(String sku) {
		produtoRepository.deleteById(sku);
		
	}

	
	private Produto entityParaDomain(ProdutoEntity produtoEntity) {
		return new Produto(
				produtoEntity.getSku(),
				produtoEntity.getNome(),
				produtoEntity.getCodigoDeBarras(),
				produtoEntity.getPreco(),
				produtoEntity.getDescricao(),
				produtoEntity.getCategoria(),
				produtoEntity.getFabricante()
				);
	}

	private ProdutoEntity domainParaEntity(Produto produto) {
		return new ProdutoEntity(
				produto.getSku(),
				produto.getNome(),
				produto.getCodigoDeBarras(),
				produto.getDescricao(),
				produto.getFabricante(),
				produto.getPreco(),
				produto.getCategoria()
				);
	}


}
