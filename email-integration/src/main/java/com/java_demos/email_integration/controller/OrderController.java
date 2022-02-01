package com.java_demos.email_integration.controller;

import com.java_demos.email_integration.integration.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class OrderController {

    @PostMapping("/receive-order")
    public void receiveOrder(@RequestBody Order order) {
        log.info("\nReceived order from receive url =" + order);
    }
}
