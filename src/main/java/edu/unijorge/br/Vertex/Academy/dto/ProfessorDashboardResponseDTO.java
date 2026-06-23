package edu.unijorge.br.Vertex.Academy.dto;

import java.util.List;

public record ProfessorDashboardResponseDTO(
        Long id,
        String nome,
        String email,
        List<String> authorities,
        String role,
        String matricula
) implements UsuarioLogadoDTO {
}
