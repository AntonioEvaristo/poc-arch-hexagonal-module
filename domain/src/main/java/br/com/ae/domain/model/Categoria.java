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
    @Positive(message = "Codigo deve ser positivo")
    @NotNull(message = "Codigo não pode ser nulo")
    private String codigo;

    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    private String nome;
}
