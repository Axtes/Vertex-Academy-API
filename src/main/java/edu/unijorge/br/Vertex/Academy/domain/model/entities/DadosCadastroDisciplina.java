package edu.unijorge.br.Vertex.Academy.domain.model.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroDisciplina(
        @NotBlank
        String nome,
        @NotNull
        Integer cargaHoraria
) {
}
