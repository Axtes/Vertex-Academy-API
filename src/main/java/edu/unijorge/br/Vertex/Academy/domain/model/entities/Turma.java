package edu.unijorge.br.Vertex.Academy.domain.model.entities;


import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.Aluno;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "turmas")
@Entity(name = "Turma")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToMany(mappedBy = "turma")
    private List<Aluno> alunos = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private Curso curso;
    @ManyToMany
    @JoinTable(
            name = "turma_disciplina",
            joinColumns = @JoinColumn(name = "turma_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    private List<Disciplina> disciplinas = new ArrayList<>();
    private String codigoTurma;
    private char referencia;
    private Integer semestre;
    private Integer ano;

    public void gerarCodigoTurma(){
        LocalDate data = LocalDate.now();
        ano = data.getYear();
        semestre = data.getMonthValue() <= 6 ? 1 : 2;

        String codigo =
                curso.getCodigoCurso()
                        + ano
                        + String.format("%02d", semestre)
                        + referencia;

        this.setCodigoTurma(codigo);
    }

    public boolean estaLotada() {
        return alunos.size() >= 40;
    }
}
