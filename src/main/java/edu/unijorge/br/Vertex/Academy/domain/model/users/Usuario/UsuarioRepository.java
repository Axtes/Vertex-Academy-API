package edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario;

import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = :login OR u.cpf = :login")
    Optional<Usuario> findByEmailOfCpf(@Param("login")String login);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    Optional<Usuario> findByNome(@NotBlank String nome);

    Optional<Usuario> findByCpf(@NotBlank String cpf);

    boolean existsByMatricula(String matricula);

    @Modifying
    @Transactional
    @Query(value = """
            UPDATE usuarios
            SET matricula = :matricula
            WHERE id = :id
            """, nativeQuery = true)
    void alocarMatricula(
            @Param("id") Long id,
            @Param("matricula") String matricula);
}
