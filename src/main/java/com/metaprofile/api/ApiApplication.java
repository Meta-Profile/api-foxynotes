package com.metaprofile.api;

import com.metaprofile.api.configuration.FileStorageConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageConfig.class
})
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

    public @Bean
    OpenAPI noteAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Meta Profile API")
                                .description("A CRUD API to demonstrate integration")
                                .version("1.0.0")
                );
    }
}
