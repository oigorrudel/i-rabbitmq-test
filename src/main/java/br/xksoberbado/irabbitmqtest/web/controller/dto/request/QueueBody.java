package br.xksoberbado.irabbitmqtest.web.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public record QueueBody(@NotBlank String name) {
}
