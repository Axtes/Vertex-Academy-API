package edu.unijorge.br.Vertex.Academy.dto;


import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;

public record UsuarioDTO(String nome, String email) {
    public UsuarioDTO(Usuario usuario){
        this(usuario.getNome(), usuario.getEmail());
    }
}
