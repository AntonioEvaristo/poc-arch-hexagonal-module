package br.com.ae.rest.controller;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.exception.ProdutoException;
import br.com.ae.domain.model.Produto;
import br.com.ae.domain.service.IProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/rest/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final IProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(@RequestParam ProdutoDisponibilidade produtoDisponibilidade,
                                                        @RequestParam(required = false) String nomeCategoria){
        return new ResponseEntity<>(produtoService.listarProdutos(produtoDisponibilidade, nomeCategoria), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable String codigo) throws ProdutoException {
        return new ResponseEntity<>(produtoService.buscarProduto(codigo), HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@Valid @RequestBody Produto produto) throws ProdutoException {
        return new ResponseEntity<>(produtoService.cadastrarProduto(produto), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizarProduto(@Valid @RequestBody Produto produto, @PathVariable Long id) throws ProdutoException {
        return new ResponseEntity<>(produtoService.atualizaProduto(produto,id), HttpStatus.OK);
    }
}
