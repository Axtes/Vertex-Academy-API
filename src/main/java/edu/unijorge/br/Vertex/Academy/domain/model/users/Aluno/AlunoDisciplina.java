package edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Disciplina;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alunos_disciplinas")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class AlunoDisciplina {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Aluno aluno;
    @ManyToOne
    private Disciplina disciplina;

    private Double nota;

    private Integer faltas;

    private Double frequencia;

    @Enumerated(EnumType.STRING)
    private Status status;
}
