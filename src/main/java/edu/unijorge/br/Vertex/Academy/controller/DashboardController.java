package edu.unijorge.br.Vertex.Academy.controller;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import edu.unijorge.br.Vertex.Academy.dto.UsuarioLogadoDTO;
import edu.unijorge.br.Vertex.Academy.service.DashboardService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/me")
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<UsuarioLogadoDTO> obterDadosDashboard(Authentication authentication) {
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        UsuarioLogadoDTO dados = dashboardService.obterDadosPainel(usuarioLogado);

        return ResponseEntity.ok(dados);
    }
}
