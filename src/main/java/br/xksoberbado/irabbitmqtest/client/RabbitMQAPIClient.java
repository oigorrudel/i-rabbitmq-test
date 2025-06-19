package br.xksoberbado.irabbitmqtest.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "rabbit-mq", url = "${feign.client.rabbitmq-api.url}")
public interface RabbitMQAPIClient {

    @GetMapping("exchanges")
    List<Object> getExchanges();

    @GetMapping("exchanges/{vhost}/{name}")
    Object getExchange(@PathVariable String vhost,
                       @PathVariable String name);
}
