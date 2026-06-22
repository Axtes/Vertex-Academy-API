package edu.unijorge.br.Vertex.Academy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    public void addCorsMapping(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(
                        "http:localhost:",
                        "http:localhost:",
                        "http://127.0.0.1:5500",
                        "http://201.32.87.205:8081",
                        "http://201.32.87.205:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE","OPTIONS", "HEAD", "TRACE", "CONNECT")
                .allowedHeaders("*");

    }
}
