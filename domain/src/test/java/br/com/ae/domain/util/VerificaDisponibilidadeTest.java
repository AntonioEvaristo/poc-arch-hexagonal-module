package br.com.ae.domain.util;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.model.Categoria;
import br.com.ae.domain.model.Produto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class VerificaDisponibilidadeTest {

    private Produto produto;
    private List<Produto> produtos;

    @Before
    public void setUp() throws Exception {
        produto = Produto.builder()
                .codigo("100")
                .nome("Garrafa")
                .valor(BigDecimal.TEN)
                .quantidade(100)
                .produtoDisponibilidade(ProdutoDisponibilidade.DISPONIVEL)
                .categoria(Categoria.builder()
                        .codigo("1")
                        .nome("utensilios")
                        .build())
                .build();
        produtos = Arrays.asList(produto,
                Produto.builder()
                .codigo("100")
                .nome("Garrafa")
                .valor(BigDecimal.TEN)
                .quantidade(100)
                .produtoDisponibilidade(ProdutoDisponibilidade.INDISPONIVEL)
                .categoria(Categoria.builder()
                        .codigo("1")
                        .nome("utensilios")
                        .build())
                .build());
    }

    @Test
    public void getProduto() {
        Optional<Produto> prod = VerificaDisponibilidade.get(Optional.of(produto));
        Assert.assertNotNull(prod.get());
        Assert.assertEquals(ProdutoDisponibilidade.DISPONIVEL, prod.get().getProdutoDisponibilidade());

    }
    @Test
    public void getProdutoEmpty() {
        Optional<Produto> prod = VerificaDisponibilidade.get(Optional.of(produtos.get(1)));
        Assert.assertFalse(prod.isPresent());
    }

    @Test
    public void getProdutos() {
        List<Produto> listaProdutos = VerificaDisponibilidade.get(produtos);
        Assert.assertFalse(listaProdutos.isEmpty());
        Assert.assertEquals(1, listaProdutos.size());
        Assert.assertEquals(produto.getCodigo(), listaProdutos.get(0).getCodigo());
    }
}