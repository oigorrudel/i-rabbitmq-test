package br.xksoberbado.irabbitmqtest.client.dto.response;

import br.xksoberbado.irabbitmqtest.domain.ExchangeType;

import java.util.Arrays;

public record RabbitMQExchangeResponse(String name,
                                       String type) {
    public ExchangeType getType() {
        return Arrays.stream(ExchangeType.values())
            .filter(type -> type.name().equalsIgnoreCase(this.type))
            .findFirst()
            .orElseThrow(() -> new EnumConstantNotPresentException(ExchangeType.class, this.type));
    }
}
