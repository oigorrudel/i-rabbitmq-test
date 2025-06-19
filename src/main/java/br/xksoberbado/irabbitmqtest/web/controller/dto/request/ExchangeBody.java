package br.xksoberbado.irabbitmqtest.web.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExchangeBody(@NotBlank String name,
                           @NotNull Type type) {
    public enum Type {
        DIRECT,
        FANOUT,
        HEADERS,
        TOPIC
    }
}
