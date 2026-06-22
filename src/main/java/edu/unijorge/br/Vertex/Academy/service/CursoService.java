package edu.unijorge.br.Vertex.Academy.service;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.CursoRepository;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.DadosCadastroCurso;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {
    @Autowired
    CursoRepository cursoRepository;

    public Curso cadastrarCurso(DadosCadastroCurso dados) {
        Curso curso = new Curso();

        curso.setTitulo(dados.titulo());
        curso.setSemestres(dados.semestres());
        curso.setDescricao(dados.descricao());

        curso = cursoRepository.save(curso);

        curso.gerarCodigoCurso();

        return cursoRepository.save(curso);

    }
}
