package edu.unijorge.br.Vertex.Academy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    public void addCprsMapping(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https:localhost:")
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS", "HEAD", "TRACE", "CONNECT");

    }
}
