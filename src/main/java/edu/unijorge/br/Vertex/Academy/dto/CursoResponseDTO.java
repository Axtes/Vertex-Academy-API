package edu.unijorge.br.Vertex.Academy.dto;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Curso;

public record CursoResponseDTO(Long id, String titulo, Integer semestres, Integer cargaHoraria, String descricao) {
    public CursoResponseDTO(Curso curso) {
        this(curso.getId(), curso.getTitulo(), curso.getSemestres(), curso.getCargaHoraria(), curso.getDescricao());
    }
}
