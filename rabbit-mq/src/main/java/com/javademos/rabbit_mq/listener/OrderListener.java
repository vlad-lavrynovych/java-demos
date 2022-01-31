package com.javademos.rabbit_mq.listener;

import com.javademos.rabbit_mq.dto.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderListener {

    @RabbitListener(queues = "tacocloud.order.queue")
    public void receiveOrder(Order order) {
        log.info("=========== Received order=\n" + order);
    }
}
