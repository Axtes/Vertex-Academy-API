package edu.unijorge.br.Vertex.Academy.domain.model.users.Administrador;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "administradores")
public class Administrador extends Usuario {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
