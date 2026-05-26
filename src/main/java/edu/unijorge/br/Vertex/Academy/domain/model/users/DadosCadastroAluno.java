package edu.unijorge.br.Vertex.Academy.domain.model.users;

import edu.unijorge.br.Vertex.Academy.domain.model.Curso;
import jakarta.validation.constraints.NotBlank;

public record DadosCadastroAluno(
        @NotBlank
        Curso curso
) {
}
