package edu.unijorge.br.Vertex.Academy.domain.model.entities;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.Aluno;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.AlunoDisciplina;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Professor.Professor;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    private String nome;
    private Integer cargaHoraria;

    @OneToMany(mappedBy = "disciplina")
    private List<AlunoDisciplina> alunos = new ArrayList<>();

    @ManyToMany(mappedBy = "disciplinas")
    private List<Curso> cursos = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "disciplinas_professores",
            joinColumns = @JoinColumn(name = "disciplina_id"),
            inverseJoinColumns = @JoinColumn(name = "professor_id")
    )
    private List<Professor> professores = new ArrayList<>();

    @Column(unique = true)
    private String codigoDisciplina;

    public void gerarCodigoDisciplina() {

        String uuid = UUID.randomUUID()
                .toString()
                .replace("-","")
                .substring(0, 6)
                .toUpperCase();

        this.codigoDisciplina = "DP" + uuid;
    }
}
