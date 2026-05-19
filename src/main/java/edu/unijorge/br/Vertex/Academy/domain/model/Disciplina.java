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
    @JoinColumn(name = "aulas_id")
    private List<Aula> aulas = new ArrayList<>();
    @JoinColumn(name = "cursos_id")
    private List<Curso> cursos = new ArrayList<>();
    @JoinColumn(name = "turmas_id")
    private List<Turma> turmas = new ArrayList<>();
    @JoinColumn(name = "alunos_id")
    private List<Aluno> alunos = new ArrayList<>();
    @JoinColumn(name = "professores_id")
    private List<Professor> professores = new ArrayList<>();
    private String codigoDisciplina;
}
