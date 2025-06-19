package br.xksoberbado.irabbitmqtest.web.controller;

import br.xksoberbado.irabbitmqtest.web.controller.dto.request.QueueBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/queues")
@RequiredArgsConstructor
public class QueueController {

    private static final String DLQ_SUFFIX = ".dlq";
    private static final String DLQ_EXCHANGE = "";

    private final AmqpAdmin amqpAdmin;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody @Valid final QueueBody body) {
        final var queueName = body.name();
        final var dlqQueueName = queueName + DLQ_SUFFIX;

        amqpAdmin.declareQueue(
            QueueBuilder.durable(dlqQueueName).build()
        );

        amqpAdmin.declareQueue(
            QueueBuilder.durable(queueName)
                .deadLetterExchange(DLQ_EXCHANGE)
                .deadLetterRoutingKey(dlqQueueName)
                .build()
        );
    }

    @DeleteMapping("{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final String name) {
        amqpAdmin.deleteQueue(name);
    }
}
