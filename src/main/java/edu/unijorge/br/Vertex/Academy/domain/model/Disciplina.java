package edu.unijorge.br.Vertex.Academy.domain.model;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Professor;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "disciplinas")
@Entity(name = "Disciplina")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Disciplina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "disciplina_id")
    private List<Aula> aulas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "disciplinas_cursos",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id")
    )
    private List<Curso> cursos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "disciplinas_turmas",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "turma_id")
    )
    private List<Turma> turmas = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "disciplinas_alunos",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "aluno_id")
    )
    private List<Aluno> alunos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "disciplinas_professores",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores = new ArrayList<>();
    private String codigoDisciplina;
}
