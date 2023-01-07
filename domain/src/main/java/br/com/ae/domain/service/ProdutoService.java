package br.com.ae.domain.service;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.exception.ProdutoException;
import br.com.ae.domain.model.Produto;
import br.com.ae.domain.repository.IProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Log4j2
public final class ProdutoService implements IProdutoService {
    private final IProdutoRepository produtoRepository;

    @Override
    public Produto buscarProduto(String codigo) throws ProdutoException {
        Produto produto = getProduto(codigo).orElseThrow(() -> {
            log.error("Produto não localizado {}", codigo);
            return new ProdutoException("Produto não localizado, verifique o codigo do produto!");
        });
        log.info("Produto {}", produto);
        return produto;
    }


    @Override
    public List<Produto> listarProdutos(ProdutoDisponibilidade produtoDisponibilidade, String nomeCategoria) {
        List<Produto> produtos = produtoRepository
                .findAll()
                .stream()
                .filter(getProdutoDisponivel(produtoDisponibilidade))
                .collect(Collectors.toList());

        if (StringUtils.isNotBlank(nomeCategoria)) {
            produtos = produtos
                    .stream()
                    .filter(getProdutoNomeCategoria(nomeCategoria))
                    .collect(Collectors.toList());
            log.info("Filtrando lista por nomeCategoria {}", nomeCategoria);
        }
        produtos = produtos
                .stream()
                .sorted(Comparator.comparing(Produto::getValor))
                .collect(Collectors.toList());
        log.info("Lista de todos produtos {}", produtos);
        return produtos;
    }

    private static Predicate<Produto> getProdutoNomeCategoria(String nomeCategoria) {
        return prd -> prd.getCategoria().getNome().equals(nomeCategoria);
    }

    private static Predicate<Produto> getProdutoDisponivel(ProdutoDisponibilidade produtoDisponibilidade) {
        return prd -> produtoDisponibilidade.equals(prd.getProdutoDisponibilidade());
    }

    @Override
    public Produto cadastrarProduto(Produto produto) throws ProdutoException {
       if(getProduto(produto.getCodigo()).isPresent()){
           log.error("Produto já existe com esse codigo! {}", produto);
           throw  new ProdutoException("Produto já existe, verifique o codigo do produto!!");
       }
        Produto produtoSave = produtoRepository.save(produto);
        log.info("Produto salvo {}", produtoSave);
        return produtoSave;
    }


    @Override
    public Produto atualizaProduto(Produto produto, Long id) throws ProdutoException {
        Produto produtoEntity = produtoRepository.findById(id).orElseThrow(() -> {
            log.error("Produto não existe! {}", produto);
            return new ProdutoException("Produto não existe, verifique o codigo/id do produto!!");
        });
        produto.setId(produtoEntity.getId());
        produto.getCategoria().setId(produtoEntity.getCategoria().getId());
        Produto produtoUpdate = produtoRepository.save(produto);
        log.info("Produto atualizado! {}", produtoUpdate);
        return produtoUpdate;

    }

    private Optional<Produto> getProduto(String codigo) {
        return produtoRepository.findByCodigo(codigo);
    }
}

