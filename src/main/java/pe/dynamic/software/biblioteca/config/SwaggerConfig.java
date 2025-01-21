package pe.dynamic.software.biblioteca.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI bibliotecaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Biblioteca API")
                        .description("API para la gestión del sistema de biblioteca - JJ Dynamic Software Solutions")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Soporte Técnico")
                                .email("soporte@dynamicsoftware.pe")
                                .url("https://dynamicsoftware.pe")));
    }
}