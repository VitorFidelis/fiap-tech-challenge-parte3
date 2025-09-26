package gateway.br.com.gateway.infrastructure.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SpringDocConfiguration {
    @Value("${springdoc.documentation.swagger.api.info.title}")
    private String title;
    @Value("${springdoc.documentation.swagger.api.info.description}")
    private String description;
    @Value("${springdoc.documentation.swagger.api.info.contact.name}")
    private String contactName;
    @Value("${springdoc.documentation.swagger.api.info.contact.email}")
    private String contactEmail;
    @Value("${springdoc.documentation.swagger.api.info.version}")
    private String version;
    @Value("${springdoc.documentation.swagger.api.info.license}")
    private String license;
    @Value("${springdoc.documentation.swagger.api.info.licenseUrl}")
    private String licenseUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearer-key",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info()
                        .title(title)
                        .description(description)
                        .contact(new Contact()
                                .name(contactName)
                                .email(contactEmail)
                        )
                        .version(version)
                        .license(new License()
                                .name(license)
                                .url(licenseUrl)
                        ));
    }

}
