package com.kreditcart.apiGateway.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
public class LoggingFilter implements GlobalFilter {
    private Logger logger = LoggerFactory.getLogger(LoggingFilter.class);

    public LoggingFilter() {
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        this.logger.info("Request path: " + String.valueOf(exchange.getRequest().getPath()));
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            this.logger.info("Response code: " + String.valueOf(exchange.getResponse().getStatusCode()));
        }));
    }
}