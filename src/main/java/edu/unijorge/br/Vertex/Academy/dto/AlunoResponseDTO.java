package edu.unijorge.br.Vertex.Academy.dto;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.Aluno;
import org.springframework.security.core.GrantedAuthority;

public record AlunoResponseDTO(Long id, String nome, String matricula, String role, String turma) {
    public AlunoResponseDTO(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getMatricula(), aluno.getAuthorities()
                .stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority).orElse("ROLE_USER"),
                aluno.getTurma() != null ? aluno.getTurma().getCodigoTurma() : "Não matriculado");
    }
}
