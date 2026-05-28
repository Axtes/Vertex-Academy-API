package edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno;

import jakarta.validation.constraints.NotNull;

public record DadosInscricaoAluno(
        @NotNull
        Long cursoId
) {
}
