package br.com.ae.domain.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Builder
@ToString
@EqualsAndHashCode
public class Categoria {
    @Positive(message = "Codigo deve ser positivo")
    @NotNull(message = "Codigo não pode ser nulo")
    private String codigo;

    @NotBlank(message = "Nome não pode ser nulo ou vazio")
    private String nome;
}
