package br.xksoberbado.irabbitmqtest.client;

import br.xksoberbado.irabbitmqtest.client.dto.response.RabbitMQExchangeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "rabbit-mq", url = "${feign.client.rabbitmq-api.url}")
public interface RabbitMQAPIClient {

    @GetMapping("exchanges/{vhost}/{name}")
    RabbitMQExchangeResponse getExchange(@PathVariable String vhost,
                                         @PathVariable String name);
}
