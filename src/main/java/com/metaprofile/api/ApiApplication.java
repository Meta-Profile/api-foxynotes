package com.metaprofile.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ApiApplication {

    String dbUrl = "jdbc:postgresql://notafoks.me:5432/metadb";
    String dbUser = "metauser";
    String dbPass = "metapass";

//    Database db = Database.Companion.connect(dbUrl, "org.postgresql.Driver", dbUser, dbPass);

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
