package edu.unijorge.br.Vertex.Academy.domain.model.entities;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    boolean existsByCodigoDisciplina(String codigoDisciplina);
}
