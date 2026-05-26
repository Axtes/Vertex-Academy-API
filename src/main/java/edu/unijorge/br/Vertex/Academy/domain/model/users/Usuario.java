package edu.unijorge.br.Vertex.Academy.domain.model.users;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.time.LocalDateTime;

import java.util.Collection;
import java.util.List;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Setter
    private String nome;
    @Setter
    private String cpf;
    @Setter
    private String email;
    @Setter
    private LocalDate dataNascimento;
    @Setter
    private String senha;
    @Setter
    private String telefone;
    @Setter
    private String matricula;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

//    public void gerarMatricula(Usuario usuarioA) {
//        LocalDateTime data = LocalDateTime.now();
//        Integer anoC = data.getYear();
//        Integer ano = anoC % 100;
//        Integer st = (data.getMonthValue() <= 6? 1:2);
//
//    }
}
