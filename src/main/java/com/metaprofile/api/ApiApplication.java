package com.metaprofile.api;

import com.metaprofile.api.configuration.FileStorageConfig;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

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
        OpenAPI openAPI = new OpenAPI()
                .info(
                        new Info()
                                .title("Meta Profile API")
                                .description("A CRUD API to demonstrate integration")
                                .version("1.0.0")
                );
        List<Server> servers = new ArrayList<>();
        servers.add(new Server()
                .description("Foxy Notes api server")
                .url("https://api.foxynotes.ru"));
        servers.add(new Server()
                .description("Localhost server")
                .url("http://localhost:8080"));
        openAPI.setServers(servers);
        return openAPI;
    }
}
