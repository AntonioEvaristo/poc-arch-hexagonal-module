package br.com.ae.integration.repository;

import br.com.ae.domain.model.Produto;
import br.com.ae.domain.repository.IProdutoRepository;
import br.com.ae.integration.mapper.ProdutoMapper;
import br.com.ae.integration.model.CategoriaEntity;
import br.com.ae.integration.model.ProdutoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ProdutoRepository implements IProdutoRepository {

    private final ProdutoEntityRepository produtoEntityRepository;
    private final CategoriaEntityRepository categoriaEntityRepository;

    @Override
    public Optional<Produto> findByCodigo(String codigo) {
        return produtoEntityRepository.findByCodigo(codigo).map(ProdutoMapper.INSTANCE::produtoEntityToProduto);
    }

    @Override
    public Produto save(Produto produto) {
        ProdutoEntity produtoEntity = produtoEntityRepository.save(getProdutoEntity(produto));
        return ProdutoMapper.INSTANCE.produtoEntityToProduto(produtoEntity);
    }

    private ProdutoEntity getProdutoEntity(Produto produto) {
        return ProdutoMapper.INSTANCE.produtoToProdutoEntitySave(produto, buscarCategoria(produto));
    }

    private CategoriaEntity buscarCategoria(Produto produto) {
        return categoriaEntityRepository.findByCodigo(produto.getCategoria().getCodigo()).orElseGet(CategoriaEntity::new);
    }

    @Override
    public List<Produto> findAll() {
        return produtoEntityRepository.findAll()
                .stream()
                .map(ProdutoMapper.INSTANCE::produtoEntityToProduto).collect(Collectors.toList());
    }

    @Override
    public Produto update(Produto produto) {
        ProdutoEntity produtoEntity = produtoEntityRepository.save(ProdutoMapper.INSTANCE.produtoToProdutoEntity(produto));
        return ProdutoMapper.INSTANCE.produtoEntityToProduto(produtoEntity);
    }

    @Override
    public Optional<Produto> findById(Long id) {
        return produtoEntityRepository.findById(id).map(ProdutoMapper.INSTANCE::produtoEntityToProduto);
    }
}
