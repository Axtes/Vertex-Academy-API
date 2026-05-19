package edu.unijorge.br.Vertex.Academy.domain.model.users;

import edu.unijorge.br.Vertex.Academy.domain.model.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.Disciplina;
import edu.unijorge.br.Vertex.Academy.domain.model.Especializacao;
import edu.unijorge.br.Vertex.Academy.domain.model.Turma;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
    private Especializacao especializacao;
    private String biografia;
    private List<Curso> cursos = new ArrayList<>();
    private List<Turma> turmas = new ArrayList<>();
    private List<Disciplina> disciplinas = new ArrayList<>();
    private List<Aluno> alunos = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of( new SimpleGrantedAuthority("TEACHER"));
    }
}
