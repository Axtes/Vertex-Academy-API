package edu.unijorge.br.Vertex.Academy.service;

import edu.unijorge.br.Vertex.Academy.domain.model.entities.Curso;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.Turma;
import edu.unijorge.br.Vertex.Academy.domain.model.entities.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class TurmaService {

    @Autowired
    TurmaRepository turmaRepository;

    public Turma obterOuCriarTurmaDisponivel(Curso curso) {
        LocalDate data = LocalDate.now();
        int ano = data.getYear();
        int semestre = data.getMonthValue() <= 6 ? 1 : 2;

        List<Turma> turmas =
                turmaRepository.findByCursoAndAnoAndSemestreOrderByReferencia(
                        curso,
                        ano,
                        semestre
                );

        for (Turma turma : turmas){
            if (turma.getAlunos().size() < 40) {
                return turma;
            }
        }

        if (turmas.size() >=3){
            throw new RuntimeException("Todas as turmas já estão lotadas.");
        }

        char referencia;

        switch (turmas.size()) {
            case 0 -> referencia = 'A';
            case 1 -> referencia = 'B';
            case 2 -> referencia = 'C';
            default -> throw new RuntimeException();
        }

        Turma novaTurma = new Turma();
        novaTurma.setCurso(curso);
        novaTurma.setAno(ano);
        novaTurma.setSemestre(semestre);
        novaTurma.setReferencia(referencia);

        novaTurma.gerarCodigoTurma(novaTurma);

        return turmaRepository.save(novaTurma);
    }
}
