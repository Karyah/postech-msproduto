package br.com.fiap.msproduto.exception;

public class ProdutoNaoExisteException extends Exception {

	public ProdutoNaoExisteException() {
		super();
	}

	public ProdutoNaoExisteException(String message) {
		super(message);
	}

}
