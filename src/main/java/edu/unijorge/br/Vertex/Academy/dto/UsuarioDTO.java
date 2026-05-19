package edu.unijorge.br.Vertex.Academy.dto;


import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario;

public record UsuarioDTO(Long id, String email) {
    public UsuarioDTO(Usuario usuario){
        this(usuario.getId(), usuario.getEmail());
    }
}
