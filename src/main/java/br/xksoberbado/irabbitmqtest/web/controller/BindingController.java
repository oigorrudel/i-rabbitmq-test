package br.xksoberbado.irabbitmqtest.web.controller;

import br.xksoberbado.irabbitmqtest.client.RabbitMQAPIClient;
import br.xksoberbado.irabbitmqtest.factory.ExchangeFactory;
import br.xksoberbado.irabbitmqtest.web.controller.dto.request.BindingBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("v1/bindings")
@RequiredArgsConstructor
public class BindingController {

    private final RabbitMQAPIClient client;
    private final AmqpAdmin amqpAdmin;
    private final ExchangeFactory exchangeFactory;

    @Value("${spring.rabbitmq.virtual-host}")
    private String vhost;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final BindingBody body) {
        final var exchange = client.getExchange(vhost, body.exchange());

        amqpAdmin.declareBinding(
            BindingBuilder
                .bind(new Queue(body.queue()))
                .to(exchangeFactory.build(exchange.getType(), exchange.name()))
                .with(
                    Optional.ofNullable(body.routingKey())
                        .orElse(body.queue())
                )
                .noargs()
        );
    }
}
