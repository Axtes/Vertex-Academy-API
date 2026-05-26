package edu.unijorge.br.Vertex.Academy.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("home")
@SecurityRequirement(name = "bearer-key")
public class AlunosController {

//    @PostMapping("/inscricao")
//    public ResponseEntity
}
