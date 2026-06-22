package edu.unijorge.br.Vertex.Academy.service;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.CursoRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Turma;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.TurmaRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.Aluno;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.UsuarioRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.AlunoRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.DadosInscricaoAluno;
import edu.unijorge.br.Vertex.Academy.dto.AlunoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoService {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    CursoRepository cursoRepository;

    @Autowired
    TurmaRepository turmaRepository;

    @Autowired
    TurmaService turmaService;

    @Transactional
    public void inscreverAluno(
            DadosInscricaoAluno dados,
            Authentication authentication
    ) {

        Usuario usuario =
                (Usuario) authentication.getPrincipal();

        if (alunoRepository.existsById(usuario.getId())) {
            throw new RuntimeException(
                    "Usuário já é aluno."
            );
        }

        Curso curso =
                cursoRepository.findById(dados.cursoId())
                        .orElseThrow(
                                () -> new RuntimeException("Curso não encontrado.")
                        );

        if (usuario.getMatricula() == null) {

            String matricula = usuario.gerarMatricula();

            while (
                    usuarioRepository.existsByMatricula(matricula)
            ) {
                matricula =
                        String.valueOf(
                                Long.parseLong(matricula) + 1
                        );
            }

            usuario.setMatricula(matricula);
            usuarioRepository.alocarMatricula(usuario.getId(), matricula);
        }

        Turma turma = turmaService.obterOuCriarTurmaDisponivel(curso);

        alunoRepository.promoverAluno(
                usuario.getId(),
                curso.getId(),
                turma.getId()
        );

        Aluno aluno =
                alunoRepository.findById(usuario.getId())
                        .orElseThrow();

        turma.getAlunos().add(aluno);

        turmaRepository.save(turma);

    }

    @Transactional(readOnly = true)
    public AlunoResponseDTO buscarDadosAluno(Long id) {

        Aluno aluno =
                alunoRepository.buscarPorIdComTurma(id)
                        .orElseThrow(
                                () -> new RuntimeException("Aluno não encontrado.")
                        );

        return new AlunoResponseDTO(aluno);
    }
}
