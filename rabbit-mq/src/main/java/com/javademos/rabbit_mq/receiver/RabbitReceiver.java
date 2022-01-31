package com.javademos.rabbit_mq.receiver;

import com.javademos.rabbit_mq.dto.Order;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;

//@Component
public class RabbitReceiver {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitReceiver(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Order receiveOrder() {
        return rabbitTemplate.receiveAndConvert(
                "tacocloud.order.queue",
                new ParameterizedTypeReference<>() {
                });
    }

}
