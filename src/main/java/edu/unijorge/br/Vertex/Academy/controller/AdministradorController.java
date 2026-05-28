package edu.unijorge.br.Vertex.Academy.controller;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.DadosCadastroCurso;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.DadosCadastroDisciplina;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Disciplina;
import edu.unijorge.br.Vertex.Academy.service.CursoService;
import edu.unijorge.br.Vertex.Academy.service.DisciplinaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("admin")
@SecurityRequirement(name = "bearer-key")
public class AdministradorController {
    @Autowired
    CursoService cursoService;

    @Autowired
    DisciplinaService disciplinaService;

    @PostMapping("/cadastrar-curso")
    public ResponseEntity<Curso> cadastrarCurso(@RequestBody @Valid DadosCadastroCurso dados) {
        Curso curso = cursoService.cadastrarCurso(dados);

        return ResponseEntity.ok(curso);
    }

    @PostMapping("/cadastrar-disciplina")
    public ResponseEntity<Disciplina> cadastrarDisciplina(@RequestBody @Valid DadosCadastroDisciplina dados) {
        Disciplina disciplina = disciplinaService.cadastrarDisciplina(dados);

        return ResponseEntity.ok(disciplina);
    }
}
