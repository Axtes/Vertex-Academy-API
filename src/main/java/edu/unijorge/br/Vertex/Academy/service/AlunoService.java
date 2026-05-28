package edu.unijorge.br.Vertex.Academy.service;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.CursoRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.UsuarioRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.Aluno;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.AlunoRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.DadosInscricaoAluno;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class AlunoService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Transactional
    public void inscreverAluno(
            DadosInscricaoAluno dados,
            Authentication authentication
    ){

        Usuario usuario = (Usuario) authentication.getPrincipal();

        Curso curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        if (alunoRepository.existsById(usuario.getId())) {
            throw new RuntimeException("Usuário já é aluno");
        }

        if (usuario.getMatricula() == null) {

            String matricula = usuario.gerarMatricula();

            while (alunoRepository.existsByMatricula(matricula)) {
                Long numero = Long.parseLong(matricula);
                matricula = String.valueOf(numero + 1);
            }

            usuario.setMatricula(matricula);

            usuarioRepository.save(usuario);
        }

        alunoRepository.promoverAluno(
                usuario.getId(),
                curso.getId()
        );
    }
}
