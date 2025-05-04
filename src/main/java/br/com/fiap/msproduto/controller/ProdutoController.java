package br.com.fiap.msproduto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import br.com.fiap.msproduto.domain.Categoria;
import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.usecases.BuscarProdutoPorSKUUseCase;
import br.com.fiap.msproduto.usecases.ListarProdutosOrdenarPorMaiorPrecoUseCase;
import br.com.fiap.msproduto.usecases.ListarProdutosOrdenarPorMenorPrecoUseCase;
import br.com.fiap.msproduto.usecases.ListarProdutosPorCategoriaUseCase;
import br.com.fiap.msproduto.usecases.ListarProdutosUseCase;

@RestController
@RequestMapping("/api")
public class ProdutoController {
	
	@Autowired
	private  ListarProdutosOrdenarPorMaiorPrecoUseCase listarProdutosOrdenarPorMaiorPreco;
	@Autowired
	private  ListarProdutosOrdenarPorMenorPrecoUseCase listarProdutosOrdenarPorMenorPreco;
	@Autowired
	private  ListarProdutosPorCategoriaUseCase listarProdutosPorCategoria;
	@Autowired
	private  ListarProdutosUseCase listarProdutos;
	@Autowired
	private  BuscarProdutoPorSKUUseCase buscarProdutoPorSKU;
	
	@GetMapping("/buscar/{sku}")
	public Produto buscarProduto(@PathVariable String sku) {
		return this.buscarProdutoPorSKU.buscar(sku);	
	}
	
	@GetMapping("/listar/todos")
	public List<Produto> listarProduto(){
		List<Produto> produtos =  listarProdutos.listarTodos();
		return produtos;
	}
	
	@GetMapping("/listar/por-categoria/{categoria}")
	public List<Produto> listarPorCategoria(@PathVariable Categoria categoria){
		return this.listarProdutosPorCategoria.listar(categoria);
	}
	
	@GetMapping("/listar/menor-preco")
	public List<Produto> listarOrdernarPorMenorPreco(){
		return this.listarProdutosOrdenarPorMenorPreco.listar();
	}
	
	@GetMapping("/listar/maior-preco")
	public List<Produto> listarOrdernarPorMaiorPreco(){
		return this.listarProdutosOrdenarPorMaiorPreco.listar();
	}

}
