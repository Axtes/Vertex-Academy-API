package edu.unijorge.br.Vertex.Academy.infra.security;

import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.Usuario;
import edu.unijorge.br.Vertex.Academy.domain.model.users.Usuario.UsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request);

        String path = request.getRequestURI();

        if (path.equalsIgnoreCase("/auth/cadastrar") || path.equalsIgnoreCase("/auth/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        if (tokenJWT != null) {
            var subject = tokenService.getSubject(tokenJWT);
            var usuarioOpt = repository.findByEmail(subject);

            if (usuarioOpt.isPresent()) {
                Usuario usuario = usuarioOpt.get();
                var authentication = new UsernamePasswordAuthenticationToken(
                        usuario,
                        null,
                        usuario.getAuthorities()
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null) {
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}
