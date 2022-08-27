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
        produtoEntityRepository.save(getProdutoEntity(produto));
        return produto;
    }

    private ProdutoEntity getProdutoEntity(Produto produto) {
        return ProdutoMapper.INSTANCE.produtoToProdutoEntity(produto, buscarCategoria(produto));
    }

    private CategoriaEntity buscarCategoria(Produto produto) {
        Optional<CategoriaEntity> categoriaEntity = categoriaEntityRepository.findByCodigo(produto.getCategoria().getCodigo());
        return categoriaEntity.orElseGet(CategoriaEntity::new);
    }

    @Override
    public List<Produto> findByCategoriaNome(String nomeCategoria) {
        return null;
    }

    @Override
    public List<Produto> findAll() {
        return produtoEntityRepository.findAll()
                .stream()
                .map(ProdutoMapper.INSTANCE::produtoEntityToProduto).collect(Collectors.toList());
    }
}
