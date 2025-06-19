package br.xksoberbado.irabbitmqtest.web.controller;

import br.xksoberbado.irabbitmqtest.factory.ExchangeFactory;
import br.xksoberbado.irabbitmqtest.web.controller.dto.request.ExchangeBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/exchanges")
@RequiredArgsConstructor
public class ExchangeController {

    private final AmqpAdmin amqpAdmin;
    private final ExchangeFactory exchangeFactory;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final ExchangeBody body) {
        amqpAdmin.declareExchange(exchangeFactory.build(body.type(), body.name()));
    }

    @DeleteMapping("{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String name) {
        amqpAdmin.deleteExchange(name);
    }
}
