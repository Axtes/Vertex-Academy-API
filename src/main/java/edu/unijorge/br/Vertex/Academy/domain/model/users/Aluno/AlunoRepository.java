package edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;


public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    boolean existsByMatricula(String matricula);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO alunos (id, curso_id)
            VALUES (:id, :cursoId)
            """, nativeQuery = true)
    void promoverAluno(Long id, Long cursoId);

}
