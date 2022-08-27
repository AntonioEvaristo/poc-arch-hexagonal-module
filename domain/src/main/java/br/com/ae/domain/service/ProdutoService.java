package br.com.ae.domain.service;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.exception.CategoriaException;
import br.com.ae.domain.exception.ProdutoException;
import br.com.ae.domain.model.Produto;
import br.com.ae.domain.repository.IProdutoRepository;
import br.com.ae.domain.util.VerificaDisponibilidade;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
public final class ProdutoService implements IProdutoService {
    private final IProdutoRepository produtoRepository;

    @Override
    public Optional<Produto> buscarProduto(String codigo) throws ProdutoException {
        if (StringUtils.isBlank(codigo)) {
            log.error("Codigo do produto não pode ser nulo ou vazio {}", codigo);
            throw new ProdutoException("Codigo do produto nulo/vazio");
        }
        Optional<Produto> produto = VerificaDisponibilidade.get(produtoRepository.findByCodigo(codigo));
        log.info("Produto localizado {}", produto);
        return produto;
    }

    @Override
    public List<Produto> listarProdutos(ProdutoDisponibilidade produtoDisponibilidade) {
        List<Produto> produtos = produtoRepository.findAll()
                .stream()
                .filter(prd -> produtoDisponibilidade.equals(prd.getProdutoDisponibilidade()))
                .collect(Collectors.toList());
        log.info("Lista de todos produtos {}", produtos);
        return produtos;
    }

    @Override
    public List<Produto> listarProdutosPorCategoria(String nomeCategoria) throws CategoriaException {
        if (StringUtils.isBlank(nomeCategoria)) {
            log.error("Nome da categoria não pode ser nulo ou vazio");
            throw new CategoriaException("Nome da categoria nulo/vazio");
        }
        List<Produto> produtos = VerificaDisponibilidade.get(produtoRepository.findByCategoriaNome(nomeCategoria));
        log.info("Lista de produtos por categoria {}", produtos);
        return produtos;
    }

    @Override
    public Optional<Produto> cadastrarProduto(Produto produto) throws ProdutoException {
        if (Boolean.TRUE.equals(getProduto(produto).isPresent())) {
            log.error("Produto já existe! {}", produto);
            throw new ProdutoException("Produto já existe!!");
        }
        Produto produtoSave = produtoRepository.save(produto);
        log.info("Produto salvo {}", produtoSave);
        return Optional.of(produtoSave);
    }

    @Override
    public Optional<Produto> atualizaProduto(Produto produto) throws ProdutoException {
        Optional<Produto> prd = getProduto(produto);
        if (prd.isEmpty()) {
            log.error("Produto não existe! {}", produto);
            throw new ProdutoException("Produto não existe!!");
        }
        Produto produtoAtualizado = produtoRepository.save(produto);
        log.info("Produto atualizado! {}", produtoAtualizado);
        return Optional.of(produtoAtualizado);
    }

    private Optional<Produto> getProduto(Produto produto) {
        return produtoRepository.findByCodigo(produto.getCodigo());
    }
}

