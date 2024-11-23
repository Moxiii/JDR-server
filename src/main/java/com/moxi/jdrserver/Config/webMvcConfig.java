package com.moxi.jdrserver.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry){
        corsRegistry.addMapping("/**")
                .allowedOrigins("http://localhost:19006/") // pour bien etre sur d'autoriser le front a faire des requete (pas obligatoire)
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowCredentials(true);
}
