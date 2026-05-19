package edu.unijorge.br.Vertex.Academy.domain.model.users;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    Optional<Usuario> findByNome(@NotBlank String nome);
}
