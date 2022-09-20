package br.com.ae.domain.model;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Builder
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Categoria {

    private Long id;

    @Positive(message = "Deve ser positivo")
    @NotNull(message = "Não pode ser nulo")
    private String codigo;

    @NotBlank(message = "Não pode ser nulo ou vazio")
    private String nome;
}
