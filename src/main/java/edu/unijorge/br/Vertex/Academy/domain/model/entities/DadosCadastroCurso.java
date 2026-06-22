package edu.unijorge.br.Vertex.Academy.domain.model.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCurso(
        @NotBlank
        String titulo,
        @NotNull
        Integer semestres,
        @NotBlank
        String descricao
) {
}
