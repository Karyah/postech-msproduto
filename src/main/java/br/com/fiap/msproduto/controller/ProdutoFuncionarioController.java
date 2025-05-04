package br.com.fiap.msproduto.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.msproduto.domain.Produto;
import br.com.fiap.msproduto.dto.ProdutoDTO;
import br.com.fiap.msproduto.usecases.CriarProdutoUseCase;
import br.com.fiap.msproduto.usecases.EditarProdutoUseCase;
import br.com.fiap.msproduto.usecases.ExcluirProdutoUseCase;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/admin")
public class ProdutoFuncionarioController {
	
	@Autowired
	private  CriarProdutoUseCase criarProduto;
	@Autowired
	private  EditarProdutoUseCase editarProduto;
	@Autowired
	private  ExcluirProdutoUseCase excluirProduto;
	
	
	@PostMapping
	@Transactional
	public ResponseEntity<Produto> criarProduto(@RequestBody ProdutoDTO produtoDTO) {
		Produto produto = this.criarProduto.criar(produtoDTO);
		return ResponseEntity.ok(produto);
	} 
	
	@PutMapping
	public ResponseEntity<Produto> editarProduto(@RequestBody ProdutoDTO produtoDTO) {
		Produto produto = this.editarProduto.editar(produtoDTO);
		return ResponseEntity.ok(produto);
	}
	
	@DeleteMapping("/{sku}")
	public ResponseEntity excluirProduto(@PathVariable String sku) {
		this.excluirProduto.excluir(sku);
		return ResponseEntity.ok().build();
	}
	

}
