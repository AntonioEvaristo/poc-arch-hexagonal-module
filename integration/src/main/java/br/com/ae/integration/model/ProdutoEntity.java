package br.com.ae.integration.model;

import br.com.ae.domain.enums.ProdutoDisponibilidade;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Produto")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String codigo;
    private String nome;
    private String descricao;
    private BigDecimal valor;
    private Integer quantidade;
    @ManyToOne
    private CategoriaEntity categoriaEntity;
    @Enumerated(EnumType.STRING)
    private ProdutoDisponibilidade produtoDisponibilidade;
    private LocalDate dataCadastro = LocalDate.now();

}
