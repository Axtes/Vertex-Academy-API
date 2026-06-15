package edu.unijorge.br.Vertex.Academy.dao;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;

public record UsuarioDAO(
        String cpf,

        String nome,

        String matricula) {
    public UsuarioDAO(Usuario usuario) {
        this(usuario.getCpf(), usuario.getNome(), usuario.getMatricula());
    }
}
