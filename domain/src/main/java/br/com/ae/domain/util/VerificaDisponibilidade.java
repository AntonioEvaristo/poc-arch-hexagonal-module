package br.com.ae.domain.util;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import br.com.ae.domain.model.Produto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class VerificaDisponibilidade {

    public static Optional<Produto> get(Optional<Produto> produto){
      if(produto.isPresent() && ProdutoDisponibilidade.DISPONIVEL.equals(produto.get().getProdutoDisponibilidade())){
        return produto;
      }
      return Optional.empty();
    }

    public static List<Produto> get(List<Produto> produtos){
        return produtos.stream().filter(prd -> ProdutoDisponibilidade.DISPONIVEL.equals(prd.getProdutoDisponibilidade())).collect(Collectors.toList());
    }
}
