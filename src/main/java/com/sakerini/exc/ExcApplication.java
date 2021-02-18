package com.sakerini.exc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients("com.sakerini.exc.datasource.api")
public class ExcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExcApplication.class, args);
    }

}
