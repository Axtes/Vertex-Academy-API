package edu.unijorge.br.Vertex.Academy.domain.model.entities;

public enum Especializacao {
    TECNICO("Técnico(a)"),
    LICENCIADO("Licenciado(a)"),
    ESPECIALISTA("Especilista(a)"),
    MESTRE("Mestre(a)"),
    DOUTOR("Doutor(a)");


    private final String especializacao;

    Especializacao(String especializacao) {
        this.especializacao = especializacao;
    }

    public String getEspecializacao() {
        return especializacao;
    }
}
