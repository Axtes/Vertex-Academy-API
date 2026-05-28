package edu.unijorge.br.Vertex.Academy.controller;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(
        @NotBlank String login,
        @NotBlank String senha

) {
}
