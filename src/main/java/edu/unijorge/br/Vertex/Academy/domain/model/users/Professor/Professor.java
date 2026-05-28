package edu.unijorge.br.Vertex.Academy.domain.model.users.Professor;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Disciplina;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Especializacao;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Turma;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.Aluno;
import jakarta.persistence.*;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "professores")
@Entity(name = "Professor")
@Setter
public class Professor extends Usuario {
    @Enumerated(EnumType.STRING)
    private Especializacao especializacao;

    private String biografia;

    @ManyToMany
    @JoinTable(
            name = "professores_cursos",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "professores_turmas",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turmas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "professores_disciplinas",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "professores_alunos",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("ROLE_TEACHER"));
    }
}
