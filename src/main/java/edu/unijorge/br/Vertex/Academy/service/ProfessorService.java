package edu.unijorge.br.Vertex.Academy.service;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Professor.DadosCadastroProfessor;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Professor.ProfessorRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Transactional
    public void cadastrarProfessor(
            DadosCadastroProfessor dados
    ) {
        Usuario usuario = usuarioRepository.findById(dados.id())
                .orElseThrow(() ->
                        new RuntimeException("Usuário não encontrado"));

        if (professorRepository.existsById(usuario.getId())) {
            throw new RuntimeException("Usuário já é um professor");
        }

        if (usuario.getMatricula() == null) {

            String matricula = usuario.gerarMatricula();

            while (usuarioRepository.existsByMatricula(matricula)) {
                Long numero = Long.parseLong(matricula);
                matricula = String.valueOf(numero + 1);
            }

            usuario.setMatricula(matricula);

            usuarioRepository.save(usuario);
        }

        professorRepository.promoverProfessor(
                usuario.getId(),
                dados.biografia(),
                dados.especializacao().name()
        );
    }
}
