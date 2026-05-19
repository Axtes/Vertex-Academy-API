package edu.unijorge.br.Vertex.Academy.controller;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario;
import edu.unijorge.br.Vertex.Academy.domain.model.users.UsuarioRepository;
import edu.unijorge.br.Vertex.Academy.dto.UsuarioDTO;
import edu.unijorge.br.Vertex.Academy.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacao dados) {
        var usuarioOpt = usuarioRepository.findByEmail(dados.email());

        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(401).body(new DadosTokenJWT("Usuário não encontrado"));
        }

        Usuario usuario = usuarioOpt.get();

        if (!passwordEncoder.matches(dados.senha(), usuario.getSenha())) {
            return ResponseEntity.status(401).body(new DadosTokenJWT("Senha inválida"));
        }

        String token = tokenService.gerarToken(usuario);
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody @Valid DadosAutenticacao dados) {
        if (usuarioRepository.findByEmail(dados.email()).isPresent()) {
            return ResponseEntity.badRequest().build();
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

        return ResponseEntity.ok(new UsuarioDTO(usuario));
    }
}
