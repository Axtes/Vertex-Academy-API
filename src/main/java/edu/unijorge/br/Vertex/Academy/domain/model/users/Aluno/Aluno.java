package edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Turma;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "alunos")
@Setter
@Getter
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "id")
public class Aluno extends Usuario {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "turma_id")
    private Turma turma;


    @OneToMany(mappedBy = "aluno")
    private List<AlunoDisciplina> disciplinas = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

}
