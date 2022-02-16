package com.java_demos.integration.integration;

import com.java_demos.integration.properties.ApiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
public class OrderMessageHandler implements GenericHandler<Order> {

    private RestTemplate rest;
    private ApiProperties apiProps;

    public OrderMessageHandler(ApiProperties apiProps, RestTemplate rest) {
        this.apiProps = apiProps;
        this.rest = rest;
    }

    @Override
    public Order handle(Order order, MessageHeaders headers) {
        rest.postForObject(apiProps.getReceiveUrl(), order, Order.class);
        return null;
    }
}
