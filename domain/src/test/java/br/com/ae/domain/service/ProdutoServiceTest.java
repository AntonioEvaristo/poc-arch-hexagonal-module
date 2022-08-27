package br.com.ae.domain.service;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.exception.CategoriaException;
import br.com.ae.domain.exception.ProdutoException;
import br.com.ae.domain.model.Categoria;
import br.com.ae.domain.model.Produto;
import br.com.ae.domain.repository.IProdutoRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ProdutoServiceTest {

    @InjectMocks
    public ProdutoService produtoService;
    @Mock
    private IProdutoRepository produtoRepository;
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Produto produto;
    private List<Produto> produtos;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
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
        produtos = Arrays.asList(Produto.builder()
                        .codigo("123")
                        .nome("FIFA2023")
                        .valor(BigDecimal.TEN)
                        .quantidade(50)
                        .produtoDisponibilidade(ProdutoDisponibilidade.DISPONIVEL)
                        .categoria(Categoria.builder()
                                .codigo("2")
                                .nome("Gamer")
                                .build())
                        .build(),
                Produto.builder()
                        .codigo("124")
                        .nome("COD")
                        .valor(BigDecimal.TEN)
                        .quantidade(50)
                        .produtoDisponibilidade(ProdutoDisponibilidade.DISPONIVEL)
                        .categoria(Categoria.builder()
                                .codigo("2")
                                .nome("Gamer")
                                .build())
                        .build(),
                Produto.builder()
                        .codigo("333")
                        .nome("Colher")
                        .valor(BigDecimal.TEN)
                        .quantidade(25)
                        .produtoDisponibilidade(ProdutoDisponibilidade.INDISPONIVEL)
                        .categoria(Categoria.builder()
                                .codigo("1")
                                .nome("utensilios")
                                .build())
                        .build());
    }

    @Test
    public void buscarProdutoComSucesso() throws ProdutoException {
        Mockito.when(produtoRepository.findByCodigo(Mockito.anyString())).thenReturn(Optional.of(produto));
        Optional<Produto> produtoRetorno = produtoService.buscarProduto(produto.getCodigo());
        Assert.assertEquals(produto.getCodigo(), produtoRetorno.get().getCodigo());
        Assert.assertEquals(ProdutoDisponibilidade.DISPONIVEL, produtoRetorno.get().getProdutoDisponibilidade());
        Assert.assertEquals(produto.getCategoria().getNome(), produtoRetorno.get().getCategoria().getNome());
        Assert.assertEquals(produto, produtoRetorno.get());
    }

    @Test
    public void buscarProdutoException() throws ProdutoException {
        expectedException.expect(ProdutoException.class);
        expectedException.expectMessage("Codigo do produto nulo/vazio");
        produtoService.buscarProduto("");

    }

    @Test
    public void listarProdutosDisponiveisComSucesso() {
        String categoria = "Gamer";
        Mockito.when(produtoRepository.findAll()).thenReturn(produtos);
        List<Produto> listaProdutos = produtoService.listarProdutos(ProdutoDisponibilidade.DISPONIVEL);
        Assert.assertFalse(listaProdutos.isEmpty());
        Assert.assertEquals(2, listaProdutos.size());
        Assert.assertEquals(categoria, listaProdutos.get(0).getCategoria().getNome());
        Assert.assertEquals(categoria, listaProdutos.get(1).getCategoria().getNome());
    }

    @Test
    public void listarProdutosIndisponiveisComSucesso() {
        Mockito.when(produtoRepository.findAll()).thenReturn(produtos);
        List<Produto> listaProdutos = produtoService.listarProdutos(ProdutoDisponibilidade.INDISPONIVEL);
        Assert.assertFalse(listaProdutos.isEmpty());
        Assert.assertEquals(1, listaProdutos.size());
        Assert.assertEquals("utensilios", listaProdutos.get(0).getCategoria().getNome());
    }

    @Test
    public void listaProdutoPorCategoriaComSucesso() throws CategoriaException {
        String nomeCategoria = "utensilios";
        Mockito.when(produtoRepository.findByCategoriaNome(nomeCategoria)).thenReturn(Arrays.asList(produto, produto));
        List<Produto> protudosOpt = produtoService.listarProdutosPorCategoria(nomeCategoria);
        Assert.assertFalse(protudosOpt.isEmpty());
        Assert.assertEquals(2, protudosOpt.size());
        Assert.assertEquals(nomeCategoria, protudosOpt.get(0).getCategoria().getNome());
        Assert.assertEquals(nomeCategoria, protudosOpt.get(1).getCategoria().getNome());
    }

    @Test
    public void listarProdutoPorCategoriaException() throws CategoriaException {
        expectedException.expect(CategoriaException.class);
        expectedException.expectMessage("Nome da categoria nulo/vazio");
        produtoService.listarProdutosPorCategoria("");
    }

    @Test
    public void cadastrarProdutoComSucesso() throws ProdutoException {
        Mockito.when(produtoRepository.findByCodigo(produto.getCodigo())).thenReturn(Optional.empty());
        Mockito.when(produtoRepository.save(produto)).thenReturn(produto);
        Optional<Produto> produtoSalvo = produtoService.cadastrarProduto(produto);
        Assert.assertTrue(produtoSalvo.isPresent());
    }

    @Test
    public void cadastrarProdutoException() throws ProdutoException {
        Mockito.when(produtoRepository.findByCodigo(Mockito.any())).thenReturn(Optional.of(produto));
        expectedException.expect(ProdutoException.class);
        expectedException.expectMessage("Produto já existe!!");
        produtoService.cadastrarProduto(produto);
    }

    @Test
    public void atualizarProdutoComSucesso() throws ProdutoException {
        Produto prd = Produto.builder()
                .codigo("100")
                .nome("Garrafa")
                .valor(BigDecimal.ONE)
                .quantidade(50)
                .produtoDisponibilidade(ProdutoDisponibilidade.DISPONIVEL)
                .categoria(Categoria.builder()
                        .codigo("1")
                        .nome("utensilios")
                        .build())
                .build();
        Mockito.when(produtoRepository.findByCodigo(Mockito.anyString())).thenReturn(Optional.of(produto));
        Mockito.when(produtoRepository.save(prd)).thenReturn(prd);
        Optional<Produto> produtoAtualizado = produtoService.atualizaProduto(prd);
        Assert.assertTrue(produtoAtualizado.isPresent());
        Assert.assertEquals(prd.getQuantidade(), produtoAtualizado.get().getQuantidade());
        Assert.assertEquals(prd.getValor(), produtoAtualizado.get().getValor());
    }

    @Test
    public void atualizarProdutoException() throws ProdutoException {
        Mockito.when(produtoRepository.findByCodigo(Mockito.anyString())).thenReturn(Optional.empty());
        expectedException.expect(ProdutoException.class);
        expectedException.expectMessage("Produto não existe!!");
        produtoService.atualizaProduto(produto);

    }
}