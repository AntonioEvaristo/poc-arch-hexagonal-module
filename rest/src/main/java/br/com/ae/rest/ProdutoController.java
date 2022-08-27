package br.com.ae.rest;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.exception.ProdutoException;
import br.com.ae.domain.model.Produto;
import br.com.ae.domain.service.IProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("v1/rest/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final IProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(@RequestParam ProdutoDisponibilidade produtoDisponibilidade){
        return new ResponseEntity<>(produtoService.listarProdutos(produtoDisponibilidade), HttpStatus.OK);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Produto> buscarProduto(@PathVariable String codigo) throws ProdutoException {
        return new ResponseEntity<>(produtoService.buscarProduto(codigo), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody @Valid Produto produto) throws ProdutoException {
        return new ResponseEntity<>(produtoService.cadastrarProduto(produto), HttpStatus.OK);
    }
}
