package edu.unijorge.br.Vertex.Academy.dto;

import java.util.List;

public record AdminDashboardResponseDTO(
        Long id,
        String nome,
        String role,
        String matricula
) implements UsuarioLogadoDTO {
}
