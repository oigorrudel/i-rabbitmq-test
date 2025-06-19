package br.xksoberbado.irabbitmqtest.web.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record BindingBody(@NotBlank String exchange,
                          @NotBlank String routingKey,
                          @NotBlank String queue) {
}
