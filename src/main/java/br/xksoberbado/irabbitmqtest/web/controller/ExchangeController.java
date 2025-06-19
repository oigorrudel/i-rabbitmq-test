package br.xksoberbado.irabbitmqtest.web.controller;

import br.xksoberbado.irabbitmqtest.web.controller.dto.request.ExchangeBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final AmqpAdmin amqpAdmin;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final ExchangeBody body) {
        amqpAdmin.declareExchange(createExchange(body));
    }

    @DeleteMapping("{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String name) {
        amqpAdmin.deleteExchange(name);
    }

    private Exchange createExchange(final ExchangeBody body) {
        return switch (body.type()) {
            case DIRECT -> new DirectExchange(body.name());
            case FANOUT -> new FanoutExchange(body.name());
            case HEADERS -> new HeadersExchange(body.name());
            case TOPIC -> new TopicExchange(body.name());
        };
    }
}
