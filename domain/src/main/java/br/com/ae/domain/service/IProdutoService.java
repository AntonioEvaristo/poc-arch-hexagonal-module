package br.com.ae.domain.service;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.exception.ProdutoException;
import br.com.ae.domain.model.Produto;

import java.util.List;

public interface IProdutoService {
    Produto buscarProduto(String codigo) throws ProdutoException;

    List<Produto> listarProdutos(ProdutoDisponibilidade produtoDisponibilidade, String nomeCategoria);

    Produto cadastrarProduto(Produto produto) throws ProdutoException;

    Produto atualizaProduto(Produto produto, Long id) throws ProdutoException;

}
