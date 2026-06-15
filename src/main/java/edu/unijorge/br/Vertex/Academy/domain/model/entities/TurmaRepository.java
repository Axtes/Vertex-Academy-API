package edu.unijorge.br.Vertex.Academy.domain.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    List<Turma> findByCursoAndAnoAndSemestreOrderByReferencia(
            Curso curso,
            Integer ano,
            Integer semestre
    );
}
