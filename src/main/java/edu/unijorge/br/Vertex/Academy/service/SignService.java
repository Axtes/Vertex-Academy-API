package edu.unijorge.br.Vertex.Academy.service;

import edu.unijorge.br.Vertex.Academy.controller.DadosCadastroUsuario;
import edu.unijorge.br.Vertex.Academy.controller.DadosTokenJWT;
import edu.unijorge.br.Vertex.Academy.controller.LoginRequest;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.UsuarioRepository;
import edu.unijorge.br.Vertex.Academy.dto.UsuarioDTO;
import edu.unijorge.br.Vertex.Academy.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignService {
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public DadosTokenJWT login(LoginRequest dados) {

        var usuario = usuarioRepository.findByEmailOfCpf(dados.login())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = tokenService.gerarToken(usuario);
        return new DadosTokenJWT(token);
    }

    public UsuarioDTO cadastrar(DadosCadastroUsuario dados) {

        if (usuarioRepository.findByEmail(dados.email()).isPresent()) {
            throw new RuntimeException("Usuário já cadastrado");
        }

        var usuario = new Usuario();
        usuario.setNome(dados.nome());
        usuario.setCpf(dados.cpf());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setEmail(dados.email());
        usuario.setTelefone(dados.telefone());
        usuario.setDataNascimento(dados.dataNascimento());
        usuario.setMatricula(dados.matricula());

        usuarioRepository.save(usuario);

        return new UsuarioDTO(usuario);
    }
}
