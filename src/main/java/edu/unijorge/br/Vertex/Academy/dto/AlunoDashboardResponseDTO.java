package edu.unijorge.br.Vertex.Academy.dto;

public record
AlunoDashboardResponseDTO(
        Long id,
        String nome,
        String role,
        String matricula,
        String turma
) implements UsuarioLogadoDTO {
}
