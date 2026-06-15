package edu.unijorge.br.Vertex.Academy.domain.model.users.Professor;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Especializacao;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{
    boolean existsByMatricula(String matricula);

    @Modifying
    @Transactional
    @Query(value = """
            INSERT INTO professores (id, biografia, especializacao)
            VALUES (:id, :biografia, :especializacao)
            """, nativeQuery = true)
    void promoverProfessor(
            @Param("id") Long id,
            @Param("biografia") String biografia,
            @Param("especializacao") String especializacao);
}
