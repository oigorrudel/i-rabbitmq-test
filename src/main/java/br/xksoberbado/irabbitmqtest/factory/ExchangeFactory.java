package br.xksoberbado.irabbitmqtest.factory;

import br.xksoberbado.irabbitmqtest.domain.ExchangeType;
import org.springframework.amqp.core.*;
import org.springframework.stereotype.Component;

@Component
public class ExchangeFactory {

    public Exchange build(final ExchangeType type,
                          final String name) {
        return switch (type) {
            case DIRECT -> new DirectExchange(name);
            case FANOUT -> new FanoutExchange(name);
            case HEADERS -> new HeadersExchange(name);
            case TOPIC -> new TopicExchange(name);
        };
    }
}
