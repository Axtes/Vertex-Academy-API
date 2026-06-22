package edu.unijorge.br.Vertex.Academy.domain.model.entities;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findByTitulo(@NotBlank String titulo);

    Page<Curso> findAll(Pageable paginacao);
}
