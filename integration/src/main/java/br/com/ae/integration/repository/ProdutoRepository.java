package br.com.ae.integration.repository;

import br.com.ae.domain.model.Produto;
import br.com.ae.domain.repository.IProdutoRepository;
import br.com.ae.integration.mapper.ProdutoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class ProdutoRepository implements IProdutoRepository {

    private final ProdutoEntityRepository produtoEntityRepository;
    @Override
    public Optional<Produto> findByCodigo(String codigo) {
        return Optional.empty();
    }

    @Override
    public Produto save(Produto produto) {
        return null;
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
