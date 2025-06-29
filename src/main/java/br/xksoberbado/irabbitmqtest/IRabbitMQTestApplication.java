package br.xksoberbado.irabbitmqtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class IRabbitMQTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(IRabbitMQTestApplication.class, args);
    }
}
