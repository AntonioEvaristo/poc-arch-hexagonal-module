package br.com.ae.domain.service;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.exception.CategoriaException;
import br.com.ae.domain.exception.ProdutoException;
import br.com.ae.domain.model.Produto;

import java.util.List;
import java.util.Optional;

public interface IProdutoService {
    Produto buscarProduto(String codigo) throws ProdutoException;

    List<Produto> listarProdutos(ProdutoDisponibilidade produtoDisponibilidade);

    List<Produto> listarProdutosPorCategoria(String nomeCategoria) throws CategoriaException;

    Produto cadastrarProduto(Produto produto) throws ProdutoException;

    Produto atualizaProduto(Produto produto) throws ProdutoException;
}
