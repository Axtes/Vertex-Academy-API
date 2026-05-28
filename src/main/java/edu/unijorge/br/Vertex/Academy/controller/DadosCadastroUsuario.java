package edu.unijorge.br.Vertex.Academy.controller;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record DadosCadastroUsuario(
        @NotBlank
        @Pattern(regexp = "^[\\p{L} .']{1,85}$")
        String nome,
        @NotBlank
        @Pattern(regexp = "^\\d{11}$")
        String cpf,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String senha,
        @NotNull
        LocalDate dataNascimento,
        @NotBlank
        @Pattern(regexp = "^\\d{1,15}$")
        String telefone,
        String matricula) {
}
