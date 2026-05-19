package edu.unijorge.br.Vertex.Academy.domain.model.users;

import edu.unijorge.br.Vertex.Academy.domain.model.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.Disciplina;
import edu.unijorge.br.Vertex.Academy.domain.model.Turma;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
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
    @JoinColumn(name = "curso_id")
    private List<Curso> cursos = new ArrayList<>();
    @JoinColumn(name = "turmas_id")
    private List<Turma> turmas = new ArrayList<>();
    @JoinColumn(name = "disciplinas_id")
    private List<Disciplina> disciplinas = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("STUDENT"));
    }

}
