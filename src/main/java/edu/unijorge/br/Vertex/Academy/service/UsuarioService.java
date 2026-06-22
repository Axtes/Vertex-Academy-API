package edu.unijorge.br.Vertex.Academy.service;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.UsuarioRepository;
import edu.unijorge.br.Vertex.Academy.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioDTO buscarPorCpf(String cpf) {
        return usuarioRepository.findByCpf(cpf)
                .map(u -> new UsuarioDTO(
                        u.getNome(),
                        u.getEmail(),
                        u.getCpf().replaceAll("(\\d{3})\\d{3}\\d{3}(\\d{2})", "$1.***.***-$2"),
                        u.getMatricula()
                ))
                .orElse(null);
    }
}
