package edu.unijorge.br.Vertex.Academy.domain.model.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String codigoCurso;
    private Integer cargaHoraria;
    private Integer semestres;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "cursos_disciplinas",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "disciplina_id")

    )
    private List<Disciplina> disciplinas = new ArrayList<>();

    public void gerarCodigoCurso(){
        String sigla = Arrays.stream(this.titulo.split(" "))
                .filter(palavra -> !palavra.isBlank())
                .map(palavra ->String.valueOf(palavra.charAt(0)).toUpperCase())
                .collect(Collectors.joining());
        this.codigoCurso = sigla + String.format("%03d", this.id);
    }
}
