package edu.unijorge.br.Vertex.Academy.domain.model.users.Professor;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Especializacao;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProfessor(
        Long id,
        Especializacao especializacao,
        @NotNull
        String biografia
) {
}
