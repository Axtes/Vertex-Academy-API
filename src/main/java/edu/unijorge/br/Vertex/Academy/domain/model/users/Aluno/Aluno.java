package edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Turma;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@Table(name = "alunos")
@Setter
@Getter
public class Aluno extends Usuario {

    @OneToOne
    private Curso curso;

    @OneToOne
    private Turma turma;


    @OneToMany(mappedBy = "aluno")
    private List<AlunoDisciplina> disciplinas = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

}
