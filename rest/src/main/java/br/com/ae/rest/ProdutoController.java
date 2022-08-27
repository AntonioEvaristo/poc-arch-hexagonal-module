package br.com.ae.rest;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.model.Produto;
import br.com.ae.domain.service.IProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/rest/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final IProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<Produto>> listarProdutos(@RequestParam ProdutoDisponibilidade produtoDisponibilidade){
        return new ResponseEntity<>(produtoService.listarProdutos(produtoDisponibilidade), HttpStatus.OK);
    }
}
