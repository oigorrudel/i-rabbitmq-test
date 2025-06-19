package br.xksoberbado.irabbitmqtest.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class RabbitMQFeignInterceptor implements RequestInterceptor {

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    @Override
    public void apply(final RequestTemplate requestTemplate) {
        final var header = "Basic " + Base64.getEncoder().encodeToString((username + ":" + password).getBytes());

        requestTemplate.header(HttpHeaders.AUTHORIZATION, header);
    }
}
