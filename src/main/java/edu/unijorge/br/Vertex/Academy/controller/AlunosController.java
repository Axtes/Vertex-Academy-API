package edu.unijorge.br.Vertex.Academy.controller;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Aluno.DadosInscricaoAluno;
import edu.unijorge.br.Vertex.Academy.service.AlunoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlunosController {

    @Autowired
    private AlunoService alunoService;

    @PostMapping("/inscricao")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity inscrever(@RequestBody @Valid DadosInscricaoAluno dados, Authentication authentication) {

        alunoService.inscreverAluno(dados, authentication);

        return ResponseEntity.ok().build();
    }
}
