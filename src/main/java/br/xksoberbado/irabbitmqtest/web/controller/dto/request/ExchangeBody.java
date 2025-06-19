package br.xksoberbado.irabbitmqtest.web.controller.dto.request;

import br.xksoberbado.irabbitmqtest.domain.ExchangeType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ExchangeBody(@NotBlank String name,
                           @NotNull ExchangeType type) {
}
