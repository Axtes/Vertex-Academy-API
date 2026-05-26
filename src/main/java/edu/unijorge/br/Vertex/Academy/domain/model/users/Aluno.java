package edu.unijorge.br.Vertex.Academy.domain.model.users;

import edu.unijorge.br.Vertex.Academy.domain.model.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.Disciplina;
import edu.unijorge.br.Vertex.Academy.domain.model.Turma;
import jakarta.persistence.*;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "alunos")
@Setter
public class Aluno extends Usuario{

    @ManyToMany
    @JoinTable(
            name = "alunos_cursos",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "alunos_turmas",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turmas = new ArrayList<>();


    @ManyToMany
    @JoinTable(
            name = "alunos_disciplinas",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_STUDENT"));
    }

}
