package edu.unijorge.br.Vertex.Academy.controller;

import edu.unijorge.br.Vertex.Academy.dto.CursoResponseDTO;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursosController {
    @Autowired
    CursoRepository cursoRepository;

    @GetMapping("/listagem")
    public ResponseEntity<Page<CursoResponseDTO>> listarCursos(@PageableDefault(size = 3, sort = "titulo")Pageable paginacao) {
        var page = cursoRepository.findAll(paginacao).map(CursoResponseDTO::new);
        return ResponseEntity.ok(page);
    }
}
