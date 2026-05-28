package edu.unijorge.br.Vertex.Academy.domain.model.entities;

public enum Status {
    ANDAMENTO("Em andamento"),
    REPROVADO("Reprovado(a)"),
    APROVADO("Aprovado(a)"),
    TRANCADO("Trancado(a)");


    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
