package edu.unijorge.br.Vertex.Academy.controller;

import java.time.LocalDate;

public record DadosAutenticacao(String nome,
                                String cpf,
                                String email,
                                String senha,
                                LocalDate dataNascimento,
                                String telefone,
                                Long matricula) {
}
