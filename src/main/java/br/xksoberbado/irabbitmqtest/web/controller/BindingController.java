package br.xksoberbado.irabbitmqtest.web.controller;

import br.xksoberbado.irabbitmqtest.web.controller.dto.request.BindingBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/bindings")
@RequiredArgsConstructor
public class BindingController {

    private final AmqpAdmin amqpAdmin;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final BindingBody body) {
        amqpAdmin.declareBinding(
            BindingBuilder
                .bind(new Queue(body.queue()))
                .to(new DirectExchange(body.exchange()))
                .withQueueName()
        );
    }
}
