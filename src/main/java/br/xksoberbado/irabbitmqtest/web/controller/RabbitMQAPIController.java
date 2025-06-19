package br.xksoberbado.irabbitmqtest.web.controller;

import br.xksoberbado.irabbitmqtest.client.RabbitMQAPIClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/rabbitmq-gateway")
@RequiredArgsConstructor
public class RabbitMQAPIController {

    private final RabbitMQAPIClient client;

    @GetMapping("exchanges")
    public List<Object> getExchanges() {
        return client.getExchanges();
    }
}
