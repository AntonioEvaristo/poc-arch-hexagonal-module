package br.com.ae.domain.model;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Builder
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Produto {

    private Long id;
    @Positive(message = "Codigo deve ser positivo")
    @NotNull(message = "Codigo não pode ser nulo")
    private String codigo;

    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    private String nome;

    @NotBlank(message = " Descrição não pode ser nula ou vazia")
    private String descricao;

    @Positive(message = "Valor deve ser positivo")
    @NotNull(message = "Valor não pode ser nulo")
    private BigDecimal valor;

    @NotNull(message = "Quantidade não pode ser nula")
    @Positive(message = "Quantidade deve ser positiva")
    private Integer quantidade;

    @NotNull(message = "Produto deve ter uma categoria")
    @Valid
    private Categoria categoria;

    @NotNull(message = "Produto Disponibilidade não pode ser nulo")
    private ProdutoDisponibilidade produtoDisponibilidade;
}
