package edu.unijorge.br.Vertex.Academy.service;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.Aluno;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.AlunoRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Professor.Professor;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Professor.ProfessorRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import edu.unijorge.br.Vertex.Academy.dto.AdminDashboardResponseDTO;
import edu.unijorge.br.Vertex.Academy.dto.AlunoDashboardResponseDTO;
import edu.unijorge.br.Vertex.Academy.dto.ProfessorDashboardResponseDTO;
import edu.unijorge.br.Vertex.Academy.dto.UsuarioLogadoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {
    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public UsuarioLogadoDTO obterDadosPainel(Usuario usuario) {
        String rolePrincipal = usuario.getAuthorities().stream()
                .findFirst()
                .map(GrantedAuthority::getAuthority)
                .orElse("ROLE_USER");

        List<String> todasAuthorities = usuario.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        if ("ROLE_ADMIN".equals(rolePrincipal)) {
            return new AdminDashboardResponseDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    rolePrincipal,
                    usuario.getMatricula()
            );
        }

        if ("ROLE_TEACHER".equals(rolePrincipal)) {

            Professor professor = professorRepository.findById(usuario.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Perfil de professor não encontrado para este usuário."));

            return new ProfessorDashboardResponseDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    usuario.getEmail(),
                    todasAuthorities,
                    rolePrincipal,
                    professor.getMatricula()
            );
        }

        if ("ROLE_STUDENT".equals(rolePrincipal)) {
            Aluno aluno = alunoRepository.findById(usuario.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Perfil de aluno não encontrado para este usuário."));

            return new AlunoDashboardResponseDTO(
                    usuario.getId(),
                    usuario.getNome(),
                    rolePrincipal,
                    aluno.getMatricula(),
                    aluno.getTurma() != null ? aluno.getTurma().getCodigoTurma() : "Não matriculado"
            );
        }

        return new AdminDashboardResponseDTO(usuario.getId(), usuario.getNome(), rolePrincipal, usuario.getMatricula());
    }
}
