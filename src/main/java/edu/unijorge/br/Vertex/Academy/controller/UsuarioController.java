package edu.unijorge.br.Vertex.Academy.controller;

import edu.unijorge.br.Vertex.Academy.dto.UsuarioDTO;
import edu.unijorge.br.Vertex.Academy.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<UsuarioDTO> buscarPorCpf(@PathVariable String cpf) {
        UsuarioDTO usuario = service.buscarPorCpf(cpf);
        return ResponseEntity.ok(usuario);
    }

}
