package br.com.ae.integration.model;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String codigo;
    private String nome;
    private BigDecimal valor;
    private Integer quantidade;
    @ManyToOne
    private CategoriaEntity categoria;
    @Enumerated(EnumType.STRING)
    private ProdutoDisponibilidade produtoDisponibilidade;
}
