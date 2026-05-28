package edu.unijorge.br.Vertex.Academy.service;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.DadosCadastroDisciplina;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Disciplina;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DisciplinaService {
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina cadastrarDisciplina(DadosCadastroDisciplina dados) {
        Disciplina disciplina = new Disciplina();

        disciplina.setNome(dados.nome());
        disciplina.setCargaHoraria(dados.cargaHoraria());

        do {
            disciplina.gerarCodigoDisciplina();
        } while (disciplinaRepository.existsByCodigoDisciplina(disciplina.getCodigoDisciplina()));

        disciplina = disciplinaRepository.save(disciplina);

        disciplina.gerarCodigoDisciplina();

        return disciplinaRepository.save(disciplina);
    }
}
