package edu.unijorge.br.Vertex.Academy.domain.model;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "aulas")
@Entity(name = "Aula")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
