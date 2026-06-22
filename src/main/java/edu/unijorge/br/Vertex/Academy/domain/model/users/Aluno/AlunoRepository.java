package edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    @Modifying
    @Transactional
    @Query(value = """
        INSERT INTO alunos(id, curso_id, turma_id)
        VALUES (:id, :cursoId, :turmaId)
        """, nativeQuery = true)
    void promoverAluno(
            @Param("id") Long id,
            @Param("cursoId") Long cursoId,
            @Param("turmaId") Long turmaId
    );

    boolean existsByIdAndCursoId(Long id, Long cursoId);

    @Query("""
    SELECT a
    FROM Aluno a
    LEFT JOIN FETCH a.turma
    WHERE a.id = :id
""")
    Optional<Aluno> buscarPorIdComTurma(
            @Param("id") Long id
    );
}
