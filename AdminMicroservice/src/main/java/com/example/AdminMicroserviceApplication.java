package com.example;

import com.google.gson.Gson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AdminMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminMicroserviceApplication.class, args);
    }

    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    @Bean
    public Gson gson(){
        return new Gson();
    }
}
